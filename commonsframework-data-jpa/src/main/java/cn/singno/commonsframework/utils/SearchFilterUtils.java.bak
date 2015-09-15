package cn.singno.commonsframework.utils;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * 名称：SearchFilterUtils.java<br>
 * 描述：动态过滤查询工具类<br>
 * 
 * <pre>
 * 简化动态查询对过滤条件的拼接操作
 * 规则：只有在传递符合要求的过滤参数时，才维护sql拼接和参数集，否则不做任何操作
 * 非动态查询时，请不要使用
 * </pre>
 * 
 * @author 周光暖
 * @date 2015-1-12 下午10:25:46
 * @version 1.0.0
 */
public class SearchFilterUtils
{
	private static final String SPOT = ".";

	/**
	 * 描述：添加过滤条件<br>
	 * 
	 * <pre>
	 * 根据添加的过滤条件，动态的构建sql，和维护params
	 * </pre>
	 * 
	 * @param sql
	 * @param params
	 * @param field
	 * @param conditions
	 */
	public static void addFilterCondition(StringBuilder sql, List<Object> params, Condition condition)
	{
		if (StringUtils.isBlank(sql) || null == params || null == condition || StringUtils.isBlank(condition.getConditionSql()))
		{
			return;
		}
		if (!StringUtils.containsIgnoreCase(sql, "where"))
		{
			sql.append(" where 1=1");
		}
		sql.append(" and " + condition.getConditionSql());
		params.addAll(condition.getParams());
	}
	
	/**
	 * 名称：Condition.java<br>
	 * 描述：过滤条件<br>
	 * 
	 * <pre>
	 * 分装过滤条件的对象
	 * </pre>
	 * 
	 * @author 周光暖
	 * @date 2015-1-12 下午10:26:49
	 * @version 1.0.0
	 */
	public interface Condition
	{
		/**
		 * 描述：获得条件语句
		 * 
		 * <pre></pre>
		 * 
		 * @return
		 */
		public String getConditionSql();

		/**
		 * 描述：获得参数
		 * 
		 * <pre></pre>
		 * 
		 * @return
		 */
		public List<Object> getParams();

		/**
		 * 描述：and 连接条件
		 * 
		 * <pre></pre>
		 * 
		 * @param condition
		 * @return
		 */
		public Condition and(Condition condition);

		/**
		 * 描述：or 连接条件
		 * 
		 * <pre></pre>
		 * 
		 * @param condition
		 * @return
		 */
		public Condition or(Condition condition);
	}

	/**
	 * 描述：等于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition equal(final String field, final Object param, final String alias)
	{
		return new SearchFilterUtils.ConditionImple(field, CompareType.equal, new Object[] { param }, alias);
	}
	
	/**
	 * 描述：等于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @return
	 */
	public static Condition equal(final String field, final Object param)
	{
		return equal(field, param, null);
	}

	/**
	 * 描述：不等于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition notEqual(final String field, final Object param, final String alias)
	{
		return new SearchFilterUtils.ConditionImple(field, CompareType.notEqual, new Object[] { param }, alias);
	}

	/**
	 * 描述：不等于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @return
	 */
	public static Condition notEqual(final String field, final Object param)
	{
		return notEqual(field, param, null);
	}

	/**
	 * 描述：大于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition gt(final String field, final Object param, final String alias)
	{
		return new SearchFilterUtils.ConditionImple(field, CompareType.gt, new Object[] { param }, alias);
	}

	/**
	 * 描述：大于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @return
	 */
	public static Condition gt(final String field, final Object param)
	{
		return gt(field, param, null);
	}

	/**
	 * 描述：大于等于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition gtq(final String field, final Object param, final String alias)
	{
		return new SearchFilterUtils.ConditionImple(field, CompareType.gtq, new Object[] { param }, alias);
	}

	/**
	 * 描述：大于等于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition gtq(final String field, final Object param)
	{
		return gtq(field, param, null);
	}

	/**
	 * 描述：小于等于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition lt(final String field, final Object param, final String alias)
	{
		return new SearchFilterUtils.ConditionImple(field, CompareType.lt, new Object[] { param }, alias);
	}
	
	/**
	 * 描述：小于等于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition lt(final String field, final Object param)
	{
		return lt(field, param, null);
	}

	/**
	 * 描述：小于等于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition ltq(final String field, final Object param, final String alias)
	{
		return new SearchFilterUtils.ConditionImple(field, CompareType.ltq, new Object[] { param }, null);
	}
	
	/**
	 * 描述：小于等于（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition ltq(final String field, final Object param)
	{
		return ltq(field, param, null);
	}

	/**
	 * 描述：like（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition like(final String field, final Object param, final String alias)
	{
		return new SearchFilterUtils.ConditionImple(field, CompareType.like, new Object[] { param }, alias);
	}

	/**
	 * 描述：like（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition like(final String field, final Object param)
	{
		return like(field, param, null);
	}

	/**
	 * 描述：not like（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition notLike(final String field, final Object param, final String alias)
	{
		return new SearchFilterUtils.ConditionImple(field, CompareType.notLike, new Object[] { param }, alias);
	}

	/**
	 * 描述：not like（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition notLike(final String field, final Object param)
	{
		return  notLike(field, param, null);
	}

	/**
	 * 描述：is null（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition isNull(final String field, final String alias)
	{
		return new SearchFilterUtils.ConditionImple(field, CompareType.isNull, null, alias);
	}

	/**
	 * 描述：is null（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition isNull(final String field)
	{
		return isNotNull(field, null);
	}

	/**
	 * 描述：is not null（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition isNotNull(final String field, final String alias)
	{
		return new SearchFilterUtils.ConditionImple(field, CompareType.isNotNull, null, alias);
	}

	/**
	 * 描述：is not null（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param
	 * @param alias
	 * @return
	 */
	public static Condition isNotNull(final String field)
	{
		return isNotNull(field, null);
	}

	/**
	 * 描述：between and（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param1
	 * @param param2
	 * @param alias
	 * @return
	 */
	public static Condition between_and(final String field, final Object param1, final Object param2, final String alias)
	{
		return new SearchFilterUtils.ConditionImple(field, CompareType.between_and, new Object[] { param1, param2 }, alias);
	}

	/**
	 * 描述：between and（过滤条件）<br>
	 * 
	 * <pre></pre>
	 * 
	 * @param field
	 * @param param1
	 * @param param2
	 * @param alias
	 * @return
	 */
	public static Condition between_and(final String field, final Object param1, final Object param2)
	{
		return between_and(field, param1, param2, null);
	}

	// ============================================================================================================
	// 私有方法   ===================================================================================================
	// ============================================================================================================

	private static class ConditionImple implements Condition
	{
		StringBuilder conditionSql;

		List<Object> params = Lists.newArrayList();

		protected ConditionImple(String field, CompareType compareType, Object[] params, String alias)
		{
			if(!CompareType.isNull.equals(compareType) && !CompareType.isNotNull.equals(compareType))
			{
				if (null == params) return;
				if (ArrayUtils.contains(params, null)) return;
				if (CompareType.between_and.equals(compareType) && params.length!=2) return;
			}
			
			switch (compareType)
			{
				case equal:
					if (null == getSqlParam(params[0])) return;
					conditionSql = new StringBuilder(getFieldDescribe(field, alias) + " =?");
					this.params.add(getSqlParam(params[0]));
					break;
				case notEqual:
					if (null == getSqlParam(params[0])) return;
					conditionSql = new StringBuilder(getFieldDescribe(field, alias) + " !=?");
					this.params.add(getSqlParam(params[0]));
					break;
				case gt:
					if (null == getSqlParam(params[0])) return;
					conditionSql = new StringBuilder(getFieldDescribe(field, alias) + " >?");
					this.params.add(getSqlParam(params[0]));
					break;
				case gtq:
					if (null == getSqlParam(params[0])) return;
					conditionSql = new StringBuilder(getFieldDescribe(field, alias) + " >=?");
					this.params.add(getSqlParam(params[0]));
					break;
				case lt:
					if (null == getSqlParam(params[0])) return;
					conditionSql = new StringBuilder(getFieldDescribe(field, alias) + " <?");
					this.params.add(getSqlParam(params[0]));
					break;
				case ltq:
					if (null == getSqlParam(params[0])) return;
					conditionSql = new StringBuilder(getFieldDescribe(field, alias) + " <=?");
					this.params.add(getSqlParam(params[0]));
					break;
				case like:
					if (null == getSqlParam(params[0])) return;
					conditionSql = new StringBuilder(getFieldDescribe(field, alias) + " like ?");
					this.params.add("%" + getSqlParam(params[0]) + "%");
					break;
				case notLike:
					if (null == getSqlParam(params[0])) return;
					conditionSql = new StringBuilder(getFieldDescribe(field, alias) + " not like ?");
					this.params.add("%" + getSqlParam(params[0]) + "%");
					break;
				case isNull:
					conditionSql = new StringBuilder(getFieldDescribe(field, alias) + " is null");
					break;
				case isNotNull:
					conditionSql = new StringBuilder(getFieldDescribe(field, alias) + " is not null");
					break;
				case between_and:
					if (null == getSqlParam(params[0])) return;
					if (null == getSqlParam(params[1])) return;
					conditionSql = new StringBuilder(getFieldDescribe(field, alias) + " between ? and ?");
					this.params.add(getSqlParam(params[0]));
					this.params.add(getSqlParam(params[1]));
					break;
			}
		}

		public String getConditionSql()
		{
			if (StringUtils.isBlank(this.conditionSql))
			{
				return "";
			}
			if (StringUtils.containsIgnoreCase(this.conditionSql, "and")
					|| StringUtils.containsIgnoreCase(this.conditionSql, "or"))
			{
				return new StringBuilder(this.conditionSql).insert(0, "(").append(")").toString();
			}
			return new String(this.conditionSql);
		}

		public List<Object> getParams()
		{
			return this.params;
		}

		public Condition and(Condition condition)
		{
			if (StringUtils.isNotBlank(this.conditionSql) && StringUtils.isNotBlank(condition.getConditionSql()))
			{
				this.conditionSql.append(" and " + condition.getConditionSql());
				this.params.addAll(condition.getParams());
			}
			else
			{
				this.conditionSql = null;
			}
			return this;
		}

		public Condition or(Condition condition)
		{
			if (StringUtils.isNotBlank(this.conditionSql) && StringUtils.isNotBlank(condition.getConditionSql()))
			{
				this.conditionSql.append(" or " + condition.getConditionSql());
				this.params.addAll(condition.getParams());
			}
			else
			{
				this.conditionSql = null;
			}
			return this;
		}

		/**
		 * 描述：获得字段描述<br>
		 * 
		 * <pre></pre>
		 * 
		 * @param field
		 * @param alias
		 * @return
		 */
		private String getFieldDescribe(String field, String alias)
		{
			if (StringUtils.isBlank(alias))
			{
				return field;
			}
			return alias + SPOT + field;
		}
	}

	private static Object getSqlParam(Object param)
	{
		if ((null != param && ClassUtils.isPrimitiveOrWrapper(param.getClass())) || param instanceof String) 
		{
			if (StringUtils.isBlank(param.toString()))
			{
				return null;
			}
			return param;
		}
		else
		{
			return param.toString();
		}
	}
	
	/**
	 * 名称：CompareType.java<br>
	 * 描述：比较类型枚举<br>
	 * 
	 * <pre>
	 * 分装比较连接符信息
	 * </pre>
	 * 
	 * @author 周光暖
	 * @date 2015-1-12 下午10:29:44
	 * @version 1.0.0
	 */
	private enum CompareType
	{
		equal, // 等于
		notEqual, // 不等于
		gt, // 大于
		gtq, // 大于等于
		lt, // 小于
		ltq, // 小于等于
		like, // 模糊比较
		notLike, // not like 模糊比较
		isNull, // is null比较
		isNotNull, // is not null比较
		between_and// 在...之间
	}
}
