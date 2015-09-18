package cn.singno.commonsframework.module.dao;

import static cn.singno.commonsframework.utils.SearchFilterUtils.equal;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;

import cn.singno.commonsframework.constants.SexEnum;
import cn.singno.commonsframework.generic.GenericTest;
import cn.singno.commonsframework.module.app.dao.UserDao2;
import cn.singno.commonsframework.module.app.entity.User;
import cn.singno.commonsframework.module.app.model.UserModel;
import cn.singno.commonsframework.security.bean.PasswordHelper;
import cn.singno.commonsframework.utils.SearchFilterUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class UserDaoTest extends GenericTest
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserDaoTest.class);
	
	@Autowired
	private PasswordHelper passwordHelper;
	
	@Autowired
	private UserDao2 userDao;
	
	/**
	 * 描述：新增
	 * <pre></pre>
	 */
	@Test
	public void testSave()
	{
		User user = new User();
		user.setAge(18);
		user.setName("singno2");
		user.setSex(SexEnum.MAN);
		user.setOrganizationId("sfdsf");
		user.setLocked(false);
		user.setSalt(passwordHelper.createSalt());
		
		String password = passwordHelper.encryptionCleartextPassword("123456", user.getCredentialsSalt());
		user.setPassword(password);
		user.setrPassword(password);
		
		userDao.save(user);
		
//		userService.save(this.createUser());
	};
	
	@Test
	public void testSaveByList() throws Exception
	{
		userDao.save(this.createUsers(10));
	}

	/**
	 * 描述：删除
	 * <pre></pre>
	 */
	@Test
	public void testDelete()
	{
		userDao.delete("4028818649b3ef020149b3ef143a0009s");
	};
	
	@Test
	@Rollback(true)
	public void testDeleteByUser() throws Exception
	{
		User user = userDao.findOne("4028818649b3ef020149b3ef14390006");
//		user.setRefrenceId("");// 必须拥有主件值
		userDao.delete(user);
	}
	
	@Test
	@Rollback(true)
	public void testDeleteByList() throws Exception
	{
		List<User> users = Lists.newArrayList();
		User user1 = new User();
		user1.setRefrenceId("4028818649b3ef020149b3ef14390005");
		User user2 = new User();
		user2.setRefrenceId("4028818649b3ef020149b3ef143a0007");
		users.add(user1);
		users.add(user2);
		userDao.delete(users);
	}

	/**
	 * 描述：修改
	 * <pre></pre>
	 * @throws Exception
	 */
	@Test
	public void testUpdateAgeById() throws Exception
	{
		logger.debug(userDao.updateAgeById(11, "2c9092cf49ac5b910149ac5b98b80005"));
	}
	
	
	/**
	 * 描述：查
	 * <pre></pre>
	 */
	@Test
	public void testCount()
	{
		logger.debug(userDao.count());
	};
	
	@Test
	public void testCount2() throws Exception
	{
		Object[] params = new Object[]{18};
		
//		String jpql = "select o from User o where o.age=?";
//		Long total = userDao.count(jpql, params);
		Long total = userDao.countByAge(18);
		
//		select
//	        count(user0_.refrenceId) as col_0_0_ 
//	    from
//	        User user0_ 
//	    where
//	        user0_.age=?
		
//		select
//	        count(*) 
//	    from
//	        User o 
//	    where
//	        o.age=?
		
		logger.debug(total);
	}
	
	@Test
	public void testExists()
	{
		boolean isSucces = userDao.exists("2c9092cf49ac55ba0149ac55c1ba0000");
		logger.debug(isSucces);
	};
	
	@Test
	public void testExistsN() throws Exception
	{
		Object[] params = new Object[]{100};
		
		String jpql = "from User o where o.age=?";
		Boolean exists = userDao.exists(jpql, params);
		exists = userDao.exists(jpql, params);
		
//		String sql = "from User o where o.age=?";
//		Long total = userDao.countN(sql, params);
		logger.debug(exists);
	}
	
	@Test
	public void testFindOne()
	{
		User user = userDao.findOne("4028818649b390830149b390955d0000");
		logger.debug(JSON.toJSONString(user));
	};

	@Test
	public void testFindAll()
	{
		List<User> users = (List<User>) userDao.findAll();
		logger.debug(users);
	};
	
	@Test
	public void testFindAllByIds()
	{
		List<String> ids = Arrays.asList(new String[]{"2c9092cf49ac55ba0149ac55c1ba0000", "2c9092cf49ac5b910149ac5b989e0000"});
		List<User> users = (List<User>) userDao.findAll(ids);
		logger.debug(users);
	}
	
	@Test
	public void testFindAllByPage()
	{
		Sort sort = new Sort(new Order(Direction.DESC, "age"), new Order(Direction.ASC, "sex"));
		Page<User> pageResult = userDao.findAll(new PageRequest(0, 1, sort));// 页面从0开始
		List<User> users = pageResult.getContent();// content 分页列表内容
		int numbet = pageResult.getNumber();// currentPage 第一页为0
		int numberOfElements = pageResult.getNumberOfElements();// list.size
		int size = pageResult.getSize();// pageSize 每页显示的记录数
		long totalElement = pageResult.getTotalElements();
		int totalPages = pageResult.getTotalPages();
		logger.debug(JSON.toJSONString(pageResult));
	}
	
	@Test
	public void testFindAllBySex()
	{
		Sort sort = new Sort(new Order(Direction.DESC, "age"), new Order(Direction.ASC, "sex"));
		Page<User> pageResult = userDao.findAllBySex(SexEnum.MAN, new PageRequest(1, 4, sort));// 页面从0开始
		List<User> users = pageResult.getContent();
		int numbet = pageResult.getNumber();// currentPage 第一页为0
		int numberOfElements = pageResult.getNumberOfElements();// list.size
		int size = pageResult.getSize();// pageSize 
		long totalElement = pageResult.getTotalElements();
		int totalPages = pageResult.getTotalPages();
		logger.debug(JSON.toJSONString(pageResult));
	}
	
	@Test
	public void testFindSimpleOneById() throws Exception
	{
		User user = userDao.findSimpleOneById("4028818649b390830149b390955d0000");
		logger.debug(JSON.toJSONString(user));
	}
	
	@Test
	public void testFindDetailOneById() throws Exception
	{
		User user = userDao.findDetailOneById("4028818649b390830149b390955d0000");
		logger.debug(JSON.toJSONString(user));
	}
	
	@Test
	public void testListDetailUser() throws Exception
	{
		List<User> users = userDao.listDetailUser();
		logger.debug(JSON.toJSONString(users));
	}
	
	@Test
	public void testListDetailUser2() throws Exception
	{
		List users = userDao.listDetailUser2();
		
//		String sql = "select distinct o.refrenceId, o.name, o.age, o.sex, o.password, o.rPassword, r.roleName from User o left join Role r on o.refrenceId=r.userId";
//		List<?> users = userDao.buildNativeQuery(UserBeam.class, sql, null);
		
		logger.debug(JSON.toJSONString(users));
	}
	
	@Test
	public void testFindOneN() throws Exception
	{
		List<Object> params = Lists.newArrayList();
		StringBuilder sql = new StringBuilder("select distinct o.refrenceId, o.name, o.age, o.sex, o.password, o.rPassword, r.roleName from User o left join Role r on o.refrenceId=r.userId");
		SearchFilterUtils.addFilterCondition(sql, params, equal("name", "list_10_singno", "o"));
		UserModel result = (UserModel) userDao.findOneN(UserModel.class, sql.toString(), params.toArray());
		logger.debug(JSON.toJSONString(result));
	}
	
	@Test
	public void testFind() throws Exception
	{
		List<Object> params = Lists.newArrayList();
		StringBuilder jpql = new StringBuilder("select o from User o");
		SearchFilterUtils.addFilterCondition(jpql, params, equal("name", "list_10_singno", "o"));
		Sort sort = new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));
//		List<User> result = (List<User>) userDao.find(jpql.toString(), params.toArray(), sort);
		List<User> result = (List<User>) userDao.find(jpql.toString(), params.toArray(), null);
		logger.debug(JSON.toJSONString(result));
	}
	
	@Test
	public void testFindN() throws Exception
	{
		String sql = "select distinct o.refrenceId, o.name, o.age, o.sex, o.password, o.rPassword, r.roleName from User o left join Role r on o.refrenceId=r.userId";
		Sort sort = new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));
//		List<UserBeam> result = (List<UserBeam>) userDao.findN(UserBeam.class, sql, null, sort);
		List<UserModel> result = (List<UserModel>) userDao.findN(UserModel.class, sql, null);
		logger.debug(JSON.toJSONString(result));
	}
	
	@Test
	public void testList() throws Exception
	{
		List<Object> params = Lists.newArrayList();
		StringBuilder jpql = new StringBuilder("select o from User o");
		SearchFilterUtils.addFilterCondition(jpql, params, equal("name", "list_10_singno", "o"));
		Sort sort = new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));
		List<User> result = (List<User>) userDao.list(jpql.toString(), params.toArray(), new PageRequest(0, 4, sort));
		logger.debug(JSON.toJSONString(result));
	}
	
	@Test
	public void testListN() throws Exception
	{
		String sql = "select distinct o.refrenceId, o.name, o.age, o.sex, o.password, o.rPassword, r.roleName from User o left join Role r on o.refrenceId=r.userId";
		Sort sort = new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));
		List<UserModel> result = (List<UserModel>) userDao.listN(UserModel.class, sql, null, new PageRequest(3, 4, sort));
		logger.debug(JSON.toJSONString(result));
	}
	
	@Test
	public void testSearch() throws Exception
	{
		List<Object> params = Lists.newArrayList();
		StringBuilder jpql = new StringBuilder("select o from User o");
		SearchFilterUtils.addFilterCondition(jpql, params, equal("name", "list_10_singno", "o"));
		Sort sort = null;//new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));
		Page<User> result = (Page<User>) userDao.search(jpql.toString(), params.toArray(), new PageRequest(0, 4, sort));
		logger.debug(JSON.toJSONString(result));
		logger.debug(result.getSort());
	}
	
	@Test
	public void testSearchN() throws Exception
	{
		String sql = "select distinct o.refrenceId, o.name, o.age, o.sex, o.password, o.rPassword, r.roleName from User o left join Role r on o.refrenceId=r.userId";
		Sort sort = new Sort(new Order(Direction.DESC, "o.age"), new Order(Direction.ASC, "o.sex"));
		Page<UserModel> result = (Page<UserModel>) userDao.searchN(UserModel.class, sql, null, new PageRequest(1, 4, sort));
		logger.debug(JSON.toJSONString(result));
		logger.debug(result.getSort());
		/*{
			"content" : [{
						"age" : 18,
						"name" : "singno",
						"password" : "123123",
						"rPassword" : "123123",
						"refrenceId" : "402881854ae915ce014ae915e4090000",
						"sex" : "MAN"
					}, {
						"age" : 18,
						"name" : "singno2",
						"password" : "123123",
						"rPassword" : "123123",
						"refrenceId" : "4028818649b390830149b390955d0000",
						"roleName" : "admin",
						"sex" : "MAN"
					}, {
						"age" : 70,
						"name" : "_singno",
						"password" : "123123",
						"rPassword" : "123123",
						"refrenceId" : "4028818649b3af8f0149b3b268580000",
						"sex" : "MAN"
					}],
			"firstPage" : true,
			"lastPage" : false,
			"number" : 0,
			"numberOfElements" : 3,
			"size" : 3,
			"totalElements" : 9,
			"totalPages" : 3
		}*/
	}
	
	@Test
	public void testSpecification() throws Exception
	{
		final String refrenceId = "4028818649b3ef020149b3ef13dd0000";
		Specification<User> spec = new Specification<User>()
		{
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb)
			{
				Predicate predicate = cb.equal(root.get("sex"), SexEnum.MAN);
				return predicate;
			}
		};
		List<User> users = userDao.findAll(spec);
        
		logger.debug(JSON.toJSONString(users));
	}
	
	@Test
	public void testname() throws Exception
	{
		System.out.println(String.format("select count(%s) from %s x", "x"));
		System.out.println(String.format("select count(x) from %s x", "x", "%s"));
//		select count(x) from %s x
//		select count(x) from x x
	}
	
	
	// =============================================================
	
	private User createUser()
	{
		return this.createUser("");
	}
	
	private User createUser(String flag)
	{
		User user = new User();
		user.setName(flag + "_singno");
		user.setAge(RandomUtils.nextInt(100));
		user.setPassword("123123");
		user.setrPassword("123123");
		user.setSex(RandomUtils.nextInt(100)%2==0 ? SexEnum.MAN:SexEnum.WOMAN);
		return user;
	}
	
	private List<User> createUsers(int listSize)
	{
		List<User> users = Lists.newArrayList();
		for (int i = 0; i < listSize; i++)
		{
			users.add(this.createUser("list_" + listSize));
		}
		return users;
	}
}
