package cn.singno.commonsframework.data.mybatis.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import cn.singno.commonsframework.bean.Page;

/**
 * 分页插件
 * 
 * 使用规则：
 * 	1、方法名包含Page，则会被拦截处理
 * 	2、方法参数包含Page对象，否则报 IllegalArgumentException("params page is null") 
 */
@SuppressWarnings("all")
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class })})
public class PageInterceptor implements Interceptor
{
	private static String dialect;
	private static String pageSqlId;

	@SuppressWarnings("unchecked")
	public Object intercept(Invocation ivk) throws Throwable
	{
		if (ivk.getTarget() instanceof RoutingStatementHandler)
		{
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) getValueByFieldName(delegate, "mappedStatement");

			if (mappedStatement.getId().matches(pageSqlId))
			{
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();
				if (parameterObject == null)
				{
					throw new IllegalArgumentException("params page is null");
				} else
				{
					Connection connection = (Connection) ivk.getArgs()[0];
					String sql = boundSql.getSql();
					String countSql = "select count(0) from (" + sql + ") myCount";
					PreparedStatement countStmt = connection.prepareStatement(countSql);
					BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
					setParameters(countStmt, mappedStatement, countBS, parameterObject);
					ResultSet rs = countStmt.executeQuery();
					Long totalElements = 0L;
					if (rs.next())
					{
						totalElements = rs.getLong(1);
					}
					rs.close();
					countStmt.close();

					Page page = null;
					if (parameterObject instanceof Page)
					{
						page = (Page) parameterObject;
					} else if (parameterObject instanceof ParamMap<?>)
					{
						ParamMap<?> params = (ParamMap<?>) parameterObject;
						OK:
						for (String key : params.keySet())
						{
							Object value = params.get(key);
							if (value instanceof Page<?>)
							{
								page = (Page<?>) value;
								break OK;
							}
						}
					} else
					{
						Field[] fields = parameterObject.getClass().getDeclaredFields();
						OK:
						for (Field field : fields)
						{
							if (Page.class.equals(field.getType()))
							{
								page = (Page) getValueByFieldName(parameterObject, field.getName());
								break OK;
							}
						}
					}
					if (null == page)
					{
						throw new IllegalArgumentException("params page is null");
					}
					page.setTotalElements(totalElements);
					Long totalPages = totalElements % page.getSize() == 0 ? totalElements / page.getSize() : totalElements / page.getSize() + 1;
					page.setTotalPages(totalPages.intValue());
					String pageSql = generatePageSql(sql, page);
					setValueByFieldName(boundSql, "sql", pageSql);
				}
			}
		}
		return ivk.proceed();
	}

	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException
	{
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null)
		{
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++)
			{
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT)
				{
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null)
					{
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass()))
					{
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName))
					{
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName()))
					{
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null)
						{
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else
					{
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null)
					{
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

	private String generatePageSql(String sql, Page page)
	{
		if (null != page && StringUtils.isNotBlank(dialect))
		{
			StringBuffer pageSql = new StringBuffer();
			if ("mysql".equals(dialect))
			{
				pageSql.append(sql);
				pageSql.append(" limit " + page.getNumber()*page.getSize() + "," + page.getSize());
			} else if ("oracle".equals(dialect))
			{
				pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
				pageSql.append(sql);
				pageSql.append(")  tmp_tb where ROWNUM<=");
				pageSql.append(page.getNumber() + page.getSize());
				pageSql.append(") where row_id>");
				pageSql.append(page.getNumber());
			}
			return pageSql.toString();
		} else
		{
			return sql;
		}
	}

	public Object plugin(Object arg0)
	{
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties p)
	{
		dialect = p.getProperty("dialect");
		if (StringUtils.isBlank(dialect))
		{
			try
			{
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e)
			{
				e.printStackTrace();
			}
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (StringUtils.isBlank(pageSqlId))
		{
			try
			{
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e)
			{
				e.printStackTrace();
			}
		}
	}

	// ==============================================================================================

	private Field getFieldByFieldName(Object obj, String fieldName)
	{
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass())
		{
			try
			{
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e)
			{
			}
		}
		return null;
	}

	private Object getValueByFieldName(Object obj, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
	{
		Field field = getFieldByFieldName(obj, fieldName);
		return getValueByField(obj, field);
	}

	private Object getValueByField(Object obj, Field field) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
	{
		Object value = null;
		if (field != null)
		{
			if (field.isAccessible())
			{
				value = field.get(obj);
			} else
			{
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	private void setValueByFieldName(Object obj, String fieldName, Object value) throws SecurityException, NoSuchFieldException, IllegalArgumentException,
	                IllegalAccessException
	{
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible())
		{
			field.set(obj, value);
		} else
		{
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
}