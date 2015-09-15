package cn.singno.commonsframework.generic;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>名称：GenericDao.java</p>
 * <p>描述：数据持久化通用DAO接口</p>
 * <pre>
 *         
 * </pre>
 * @author 周光暖
 * @date 2015-1-5 下午1:32:02
 * @version 1.0.0
 */
public interface GenericDao<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>
{
	/**
	 * 保存
	 * ID==null：新增
	 * ID!=null：修改
	 */
	<S extends T> S save(S entity);
	
	/**
	 * 批量保存
	 */
	<S extends T> List<S> save(Iterable<S> entities);

	/**
	 * 根据ID删除
	 */
	void delete(ID id);
	
	/**
	 * 根据实体删除（实体必须拥有ID值，否则会报空指针）
	 */
	void delete(T entity);
	
	/**
	 * 批量删除
	 */
	void delete(Iterable<? extends T> entities);
	
	/**
	 * 删除所有（慎用）
	 */
	void deleteAll();

	/**
	 * 判断记录是否存在（根据ID查询）
	 */
	boolean exists(ID id);

	/**
	 * 统计（全部）
	 */
	long count();
	
	/**
	 * 查询单条记录（根据ID查询）
	 */
	T findOne(ID id);
	
	/**
	 * 列表
	 */
	List<T> findAll();
	
	/**
	 * 列表（根据ID集查询）
	 */
	List<T> findAll(Iterable<ID> ids);
	
	/**
	 * 列表（带排序）
	 */
	List<T> findAll(Sort sort);
	
	/**
	 * 搜索（返回分页对象）
	 */
	Page<T> findAll(Pageable pageable);

	

	
	T findOne(Specification<T> spec);
	List<T> findAll(Specification<T> spec);
	Page<T> findAll(Specification<T> spec, Pageable pageable);
	List<T> findAll(Specification<T> spec, Sort sort);
	long count(Specification<T> spec);
	
	// ===========================================================================================================
	// 自定义方法 =================================================================================================
	// ===========================================================================================================
	
	/**
	 * 支持原生sql
	 * 更新记录，并返回受影响的行数（按条件）
	 * <pre>
	 * update table set flag=已预定 where flag=未预定
	 * </pre>
	 */
	public int execute(String sql, Object... params);
	
	/**
	 * 支持原生sql
	 * 统计（按条件）
	 * <pre>
	 * 1：select count(o) from o where ...
	 * 2：from o where ...
	 * </pre>
	 */
	long count(String sql, Object... params);
	
	/**
	 * 支持原生sql
	 * 判断记录是否存在（按条件）
	 * <pre>
	 * 1：select count(o) from o where ...
	 * 2：from o where ...
	 * </pre>
	 */
	boolean exists(String sql, Object... params);
	
	/**
	 * 查询单条记录（按条件）
	 */
	T findOne(String jpql, Object... params);
	
	/**
	 * 原生sql
	 * 查询单条记录（按条件）
	 */
	Object findOneN(Class<?> resultClass, String sql, Object... params);
	
	/**
	 * 列表
	 */
	List<T> find(String jpql, Object[] params);
	
	/**
	 * 描述：列表（带排序）<br>
	 * <pre></pre>
	 * @param resultClass
	 * @param sql
	 * @param params
	 * @param sort		排序对象
	 * 		Sort sort = new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));// 排序对象
	 * 
	 * @return
	 */
	List<T> find(String jpql, Object[] params, Sort sort);
	
	/**
	 * 原生sql
	 * 列表
	 */
	List<?> findN(Class<?> resultClass, String sql, Object[] params);
	
	/**
	 * 原生sql
	 * 描述：列表（带排序）<br>
	 * <pre></pre>
	 * @param resultClass
	 * @param sql
	 * @param params
	 * @param sort		排序对象
	 * 		Sort sort = new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));// 排序对象
	 * 
	 * @return
	 */
	List<?> findN(Class<?> resultClass, String sql, Object[] params, Sort sort);
	
	/**
	 * 描述：分页列表<br>
	 * 
	 * @param jpql
	 * @param pageable		分页请求参数对象
	 * 		Sort sort = new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));// 排序对象
	 * 		Pageable page = new PageRequest(0, 3); // 0：表示第一页
	 * 
	 * @return
	 */
	List<T> list(String jpql, Object[] params, Pageable pageable);
	
	/**
	 * 原生sql
	 * 描述：分页列表<br>
	 * 
	 * @param resultClass
	 * @param sql
	 * @param params
	 * @param pageable		分页请求参数对象
	 * 		Sort sort = new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));// 排序对象
	 * 		Pageable page = new PageRequest(0, 3); // 0：表示第一页
	 * 			
	 * @return
	 */
	List<?> listN(Class<?> resultClass, String sql, Object[] params, Pageable pageable);
	
	/**
	 * 原生sql
	 * 描述：搜索（返回分页对象）<br>
	 * @param jpql			
	 * @param params		
	 * @param pageable		分页请求参数对象
	 * 		Sort sort = new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));// 排序对象
	 * 		Pageable page = new PageRequest(0, 3); // 0：表示第一页
	 * 
	 * @return Page		分页结果对象
	 * 		content：			结果集	
	 *  	firstPage：true		是否第一页
	 *		lastPage：false   	是否最后一页
	 *		number：0        	当前页（0 表示第一页）
	 *		numberOfElements：3  当前页的记录数
	 *		size：3          	每页最大显示的记录数
	 *		totalElements：9		总记录数
	 *		totalPages：3       	总页数
	 */
	Page<T> search(String jpql, Object[] params, Pageable pageable);
	
	/**
	 * 原生sql
	 * 描述：搜索（返回分页对象）<br>
	 * @param resultClass	分装记录数据的类型
	 * @param sql			
	 * @param params		
	 * @param pageable		分页请求参数对象
	 * 		Sort sort = new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));// 排序对象
	 * 		Pageable page = new PageRequest(0, 3， sort); // 0：表示第一页
	 * 
	 * @return Page		分页结果对象
	 *  	content：			结果集	
	 *  	firstPage：true		是否第一页
	 *		lastPage：false   	是否最后一页
	 *		number：0        	当前页（0 表示第一页）
	 *		numberOfElements：3  当前页的记录数
	 *		size：3          	每页最大显示的记录数
	 *		totalElements：9		总记录数
	 *		totalPages：3       	总页数
	 */
	Page<?> searchN(Class<?> resultClass, String sql, Object[] params, Pageable pageable);
}
