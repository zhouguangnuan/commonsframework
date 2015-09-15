package cn.singno.commonsframework.module.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cn.singno.commonsframework.module.app.model.UserModel;

public class UserDao2Impl
{
	@PersistenceContext
	protected EntityManager em;

	// public User findDetailOneById(String refrenceId){
	// Query query =
	// em.createQuery("select new User(o.refrenceId,o.name,o.age,o.sex,o.password,o.rPassword,r.name) from User o,Role r where o.refrenceId=r.userId and o.refrenceId=?1");
	// query.setParameter(1, refrenceId);
	// return (User) query.getSingleResult();
	// };

	// 这种方式可以将数据分装到 UserBeam 中
	public List<UserModel> listDetailUser2()
	{
		String sql = "select distinct o.refrenceId, o.name, o.age, o.sex, o.password, o.rPassword, r.roleName from User o left join Role r on o.refrenceId=r.userId";
		Query query = em.createNativeQuery(sql, UserModel.class);
		return query.getResultList();
	};

}
