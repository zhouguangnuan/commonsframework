package cn.singno.commonsframework.module.dao;

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
import cn.singno.commonsframework.module.app.dao.RoleDao;
import cn.singno.commonsframework.module.app.dao.UserDao2;
import cn.singno.commonsframework.module.app.entity.Role;
import cn.singno.commonsframework.module.app.entity.User;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class RoleDaoTest extends GenericTest
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RoleDaoTest.class);
	
	@Autowired
	private UserDao2 userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 描述：新增
	 * <pre></pre>
	 */
	@Test
	public void testSave()
	{
		Role role = new Role();
		role.setName("admin");
		role.setDescription("超級管理員");
		roleDao.save(role);
	};
	
	@Test
	public void testSaveByList() throws Exception
	{
		List<Role> roles = Lists.newArrayList();
		Role role1 = new Role();
		role1.setName("admin");
		role1.setDescription("超級管理員");
		Role role2 = new Role();
		role2.setName("admin");
		role2.setDescription("超級管理員");
		Role role3 = new Role();
		role3.setName("admin");
		role3.setDescription("超級管理員");
		
		roles.add(role1);
		roles.add(role2);
		roles.add(role3);
		
		roleDao.save(roles);
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
	public void testExists()
	{
		boolean isSucces = userDao.exists("2c9092cf49ac55ba0149ac55c1ba0000");
		logger.debug(isSucces);
	};
	
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
		Page<User> pageResult = userDao.findAll(new PageRequest(1, 4, sort));// 页面从0开始
		List<User> users = pageResult.getContent();
		int numbet = pageResult.getNumber();// currentPage 第一页为0
		int numberOfElements = pageResult.getNumberOfElements();// list.size
		int size = pageResult.getSize();// pageSize
		long totalElement = pageResult.getTotalElements();
		int totalPages = pageResult.getTotalPages();
		logger.debug(JSON.toJSONString(pageResult));
	}
	
	@Test
	public void testFindByCondition() throws Exception
	{
		Specification<User> spec = new Specification<User>()
		{
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb)
			{
				return cb.equal(root.get("refrenceId"), "4028818649b3ef020149b3ef13dd0000");
			}
		};
		List<User> users = userDao.findAll(spec);
		logger.debug(users);
	}
	
	@Test
	public void testFindSimpleOneById() throws Exception
	{
		User user = userDao.findSimpleOneById("4028818649b390830149b390955d0000");
		logger.debug(JSON.toJSONString(user));
	}
	
	@Test
	public void testExecute() throws Exception
	{
		String sql = "update role set roleName=? where refrenceId=?";
		int upRows = userDao.execute(sql, "sddfsdf", "2c9090b64bce9730014bce9739800000");
		logger.debug("影响的行数：" + upRows);	
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
