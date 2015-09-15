package cn.singno.commonsframework.utils;
///**<p>项目名：</p>
// * <p>包名：	cn.singno.commonsframework.util</p>
// * <p>文件名：ValidateUtil.java</p>
// * <p>版本信息：</p>
// * <p>日期：2014年7月24日-下午6:48:25</p>
// * Copyright (c) 2014singno公司-版权所有
// */
//package cn.singno.commonsframework.util;
//
//import java.util.List;
//
//import org.apache.commons.lang.ArrayUtils;
//import org.junit.Test;
//
//import cn.singno.commonsframework.bean.User;
//import cn.singno.commonsframework.exception.ValidatingException;
//
///**
// * <p>
// * 名称：ValidateUtil.java
// * </p>
// * <p>
// * 描述：
// * </p>
// * 
// * <pre>
// * 
// * </pre>
// * 
// * @author 周光暖
// * @date 2014年7月24日 下午6:48:25
// * @version 1.0.0
// */
//public class ValidateUtilTest
//{
//	@Test
//	public void testValidate() throws Exception
//	{
//		User user = new User();
//		user.setName("");
//		user.setAge(5);
//		user.setSex((short) 3);
//
//		try
//		{
//			ValidateUtil.validate(user);
//		} catch (ValidatingException e)
//		{
//			// System.out.println(ValidateUtil.toPromptInfo(e));
//			System.out.println(ValidateUtil.toPromptInfo(e, false));
//		}
//	}
//
//	@Test
//	public void testGetMessageList() throws Exception
//	{
//		User user = new User();
//		user.setName("");
//		user.setAge(5);
//		user.setSex((short) 3);
//
//		List<String> errorList = ValidateUtil.getMessageList(user, false);// [age：年龄不能小于18岁,
//																			// name：姓名不能为空,
//																			// sex：性别不存在]
//		// List<String> errorList = ValidateUtil.getMessageList(user);//
//		// [年龄不能小于18岁, 姓名不能为空, 性别不存在]
//		System.out.println(ArrayUtils.toString(errorList));
//	}
//
//	@Test
//	public void testValidateProperty() throws Exception
//	{
//		User user = new User();
//		user.setName("");
//		user.setAge(5);
//		user.setSex((short) 3);
//
//		try
//		{
//			ValidateUtil.validateProperty(user, "sex");// 120002:参数异常 [性别不存在]
//			// ValidateUtil.validateProperty(user, "sex", false);// 120002:参数异常
//			// [sex：性别不存在]
//		} catch (ValidatingException e)
//		{
//			System.out.println(ValidateUtil.toPromptInfo(e));// 参数异常 [sex：性别不存在]
//			// System.out.println(ValidateUtil.toPromptInfo(e, false));//
//			// 120002：参数异常[性别不存在]
//		}
//	}
//
//	@Test
//	public void testValidateValue() throws Exception
//	{
//		try
//		{
//			ValidateUtil.validateValue(User.class, "sex", 2, false);// 120002:参数异常
//																	// [性别不存在]
//			// ValidateUtil.validateProperty(user, "sex", false);// 120002:参数异常
//			// [sex：性别不存在]
//		} catch (ValidatingException e)
//		{
//			System.out.println(ValidateUtil.toPromptInfo(e));// 参数异常 [sex：性别不存在]
//			// System.out.println(ValidateUtil.toPromptInfo(e, false));//
//			// 120002：参数异常[性别不存在]
//		}
//	}
//}
