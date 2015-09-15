package cn.singno.commonsframework.module.app.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.singno.commonsframework.constants.SexEnum;
import cn.singno.commonsframework.generic.GenericDao;
import cn.singno.commonsframework.module.app.entity.User;
import cn.singno.commonsframework.module.app.model.UserModel;

public interface UserDao2 extends GenericDao<User, String>
{
	@Modifying
	@Query("update User u set u.age=?1 where refrenceId=?2")
	public int updateAgeById(Integer age, String refrenceId);

	@Query("select new User(refrenceId, name, age) from User where refrenceId=?1")
	public User findSimpleOneById(String refrenceId);
	
	@Query("select new User(o.refrenceId,o.name,o.age,o.sex,o.password) from User o where o.refrenceId=?1")
	public User findDetailOneById(String refrenceId);

	@Query("select new User(o.refrenceId,o.name,o.age,o.sex,o.password) from User o")
	public List<User> listDetailUser();
	
	// 这里有个问题，不能把数据分装到 UserBeam 中，只会是List<Object[]>
//	@Query(nativeQuery=true, value="select distinct o.refrenceId, o.name, o.age, o.sex, o.password, o.rPassword, r.name from User o left join Role r on o.refrenceId=r.userId")
	public List<UserModel> listDetailUser2();
	
	@Query("select o from User o where o.sex=?1")
	Page<User> findAllBySex(SexEnum sex, Pageable pageable);
	
	@Query(countQuery="select o from User o where o.age=?")
	public long countByAge(Integer age);
	
	
}
