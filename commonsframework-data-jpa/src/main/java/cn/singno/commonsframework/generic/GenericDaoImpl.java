package cn.singno.commonsframework.generic;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;


@SuppressWarnings("all")
public class GenericDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements GenericDao<T, ID>
{
	@PersistenceContext
	private EntityManager em;
	
	public GenericDaoImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.em = entityManager;
	}

	public GenericDaoImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	
	// ===========================================================================================================
	// 自定义方法 =================================================================================================
	// ===========================================================================================================
	
	@Override
	public int execute(String sql, Object... params)
	{
		Query query = this.buildNativeQuery(sql, params);
		return query.executeUpdate();
	}
	
	@Override
	public long count(String sql, Object... params)
	{
		Query query = this.buildNativeQuery(this.translateToCountSql(sql), params);
		return executeCount(query, true);
	}

	@Override
	public boolean exists(String sql, Object... params)
	{
		return this.count(sql, params) > 0;
	}

	@Override
	public T findOne(String jpql, Object... params)
	{
		List<T> result = this.find(jpql, params);
		if (CollectionUtils.isEmpty(result))
		{
			return null;
		}
		return result.get(0);
	}

	@Override
	public Object findOneN(Class<?> resultClass, String sql, Object... params)
	{
		List result = this.findN(resultClass, sql, params);
		if (CollectionUtils.isEmpty(result))
		{
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<T> find(String jpql, Object[] params)
	{
		return this.find(jpql, params, null);
	}

	@Override
	public List<T> find(String jpql, Object[] params, Sort sort)
	{
		Query query = this.buildQuery(this.appendOrders(jpql, sort), params);
		List<T> content = query.getResultList();
		return content;
	}

	@Override
	public List<?> findN(Class<?> resultClass, String sql, Object[] params)
	{
		return this.findN(resultClass, sql, params, null);
	}

	@Override
	public List<?> findN(Class<?> resultClass, String sql, Object[] params, Sort sort)
	{
		Query query = this.buildNativeQuery(resultClass, this.appendOrders(sql, sort), params);
		List content = query.getResultList();
		return content;
	}

	@Override
	public List<T> list(String jpql, Object[] params, Pageable pageable)
	{
		if (null == pageable)
		{
			return find(jpql, params);
		}
		
		Query query = this.buildQuery(this.appendOrders(jpql, pageable.getSort()), params);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		List<T> content = query.getResultList();
		return content;
	}

	@Override
	public List<?> listN(Class<?> resultClass, String sql, Object[] params, Pageable pageable)
	{
		if (null == pageable)
		{
			return findN(resultClass, sql, params);
		}
		
		Query query = this.buildNativeQuery(resultClass, this.appendOrders(sql, pageable.getSort()), params);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		List content = query.getResultList();
		return content;
	}

	@Override
	public Page<T> search(String jpql, Object[] params, Pageable pageable)
	{
		Assert.notNull(pageable, "page not be null");
		
		Query query = this.buildQuery(this.appendOrders(jpql, pageable.getSort()), params);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		Long total = this.count(jpql, params);
		List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T> emptyList();
		return new PageImpl<T>(content, pageable, total);
	}

	@Override
	public Page<?> searchN(Class<?> resultClass, String sql, Object[] params, Pageable pageable)
	{
		Assert.notNull(pageable, "page not be null");
		
		Query query = this.buildNativeQuery(resultClass, this.appendOrders(sql, pageable.getSort()), params);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		Long total = this.count(sql, params);
		List content = total > pageable.getOffset() ? query.getResultList() : Collections.<T> emptyList();
		return new PageImpl(content, pageable, total);
	}
	
	// ===========================================================================================================
	// 私有方法 ===================================================================================================
	// ===========================================================================================================
	
	/**
	 * 描述：构建jpql Query<br>
	 * <pre></pre>
	 * @param jpql
	 * @param params
	 * @return
	 */
	public Query buildQuery(String jpql, Object[] params)
	{
		Query query = em.createQuery(jpql);
		this.setParams(query, params);
		return query;
	}
	
	/**
	 * 描述：构建原生sql Query<br>
	 * <pre></pre>
	 * @param sql
	 * @param params
	 * @param resultClass
	 * @return
	 */
	public Query buildNativeQuery(Class<?> resultClass, String sql, Object[] params)
	{
		Query query;
		if (null == resultClass)
		{
			query = em.createNativeQuery(sql);
		}
		else
		{
			query = em.createNativeQuery(sql, resultClass);
		}
		this.setParams(query, params);
		return query;
	}
	
	public Query buildNativeQuery(String sql, Object[] params)
	{
		return this.buildNativeQuery(null, sql, params);
	}
	
	/**
	 * 描述：执行统计查询<br>
	 * <pre></pre>
	 * @param query
	 * @return
	 */
	private long executeCount(Query query, boolean isNativeQuery)
	{
		List totals = query.getResultList();
		Long total = 0L;
		if (!CollectionUtils.isEmpty(totals))
		{
			for (int i = 0; i < totals.size(); i++)
			{
				Object num = totals.get(i);
				if (num instanceof BigInteger)
				{
					total += num == null ? 0 : ((BigInteger) num).longValue();
				}
				else
				{
					total += num == null ? 0 : ((Long) num).longValue();
				}
			}
		}
		return total;
	}
	
	/**
	 * 描述：转换sql为count sql<br>
	 * @param sql
	 * @return
	 */
    private String translateToCountSql(String sql)
    {
		Assert.isTrue(StringUtils.isNotBlank(sql), "sql not be null");
    	
		String resultSql;
		if (sql.toUpperCase().contains("DISTINCT"))// 统计去重后的结果集
		{
			resultSql = "select count(*) from (" + sql + ") alias";
		}
		else
		{
			int beginPos = sql.toLowerCase().indexOf("from");
			resultSql = "select count(*) " + sql.substring(beginPos);
		}
        return resultSql;
    }

    /**
     * 描述：添加排序条件<br>
     * <pre></pre>
     * @param sql
     * @return
     */
    private String appendOrders(String sql, Sort sort)
    {
    	if (null == sort)
		{
			return sql;
		}
    	
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find())
        {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        sb.append(" order by ").append(sort.toString().replaceAll(":", ""));
        return sb.toString();
    }
    
	/**
	 * 描述：设置查询参数<br>
	 * <pre></pre>
	 * @param query
	 * @param params
	 */
	private void setParams(Query query, Object[] params) {
		if(ArrayUtils.isNotEmpty(params)){
			for(int i = 0; i < params.length; i++){
				query.setParameter(i + 1, params[i]);
			}
		}
	}
}
