package cn.singno.commonsframework.data.jpa.bean;

import static cn.singno.commonsframework.utils.SearchFilterUtils.addFilterCondition;
import static cn.singno.commonsframework.utils.SearchFilterUtils.between_and;
import static cn.singno.commonsframework.utils.SearchFilterUtils.equal;
import static cn.singno.commonsframework.utils.SearchFilterUtils.gt;
import static cn.singno.commonsframework.utils.SearchFilterUtils.gtq;
import static cn.singno.commonsframework.utils.SearchFilterUtils.isNotNull;
import static cn.singno.commonsframework.utils.SearchFilterUtils.isNull;
import static cn.singno.commonsframework.utils.SearchFilterUtils.like;
import static cn.singno.commonsframework.utils.SearchFilterUtils.lt;
import static cn.singno.commonsframework.utils.SearchFilterUtils.ltq;
import static cn.singno.commonsframework.utils.SearchFilterUtils.notEqual;
import static cn.singno.commonsframework.utils.SearchFilterUtils.notLike;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;


public class SearchFilterUtilsTest
{
	StringBuilder sql = new StringBuilder("select o from User o where 1=1");
	List<Object> params = Lists.newArrayList();
	
	@Test
	public void testEqual() throws Exception
	{
		addFilterCondition(sql, params, equal("name", "singno", "o").and(equal("age", 2, "o").or(equal("sex", "MAN", "o"))));
		System.out.println(sql.toString());
		System.out.println(params.toString());
		//select o from User o where 1=1 and (o.name =? and o.age =?)
		//[singno, 1]
	}
	
	@Test
	public void testNotEqual() throws Exception
	{
		addFilterCondition(sql, params, notEqual("name", "", "o").and(notEqual("age", 1, "o")).or(notEqual("sex", "MAN", "o")));
		addFilterCondition(sql, params, notEqual("name", "singno", "o").and(notEqual("age", 1, "o")).or(notEqual("sex", "MAN", "o")));
		System.out.println(sql.toString());
		System.out.println(params.toString());
	}
	
	@Test
	public void testGt() throws Exception
	{
		addFilterCondition(sql, params, gt("name", "", "o").and(gt("age", 1, "o")).or(gt("sex", "MAN", "o")));
		addFilterCondition(sql, params, gt("name", "singno", "o").and(gt("age", 1, "o")).or(gt("sex", "MAN", "o")));
		System.out.println(sql.toString());
		System.out.println(params.toString());
	}
	
	@Test
	public void testGtq() throws Exception
	{
		addFilterCondition(sql, params, gtq("name", "", "o").and(gtq("age", 1, "o")).or(gtq("sex", "MAN", "o")));
		addFilterCondition(sql, params, gtq("name", "singno", "o").and(gtq("age", 1, "o")).or(gtq("sex", "MAN", "o")));
		System.out.println(sql.toString());
		System.out.println(params.toString());
	}
	
	@Test
	public void testLt() throws Exception
	{
		addFilterCondition(sql, params, lt("name", "", "o").and(lt("age", 1, "o")).or(lt("sex", "MAN", "o")));
		addFilterCondition(sql, params, lt("name", "singno", "o").and(lt("age", 1, "o")).or(lt("sex", "MAN", "o")));
		System.out.println(sql.toString());
		System.out.println(params.toString());
	}
	
	@Test
	public void testLtq() throws Exception
	{
		addFilterCondition(sql, params, ltq("name", "", "o").and(ltq("age", 1, "o")).or(ltq("sex", "MAN", "o")));
		addFilterCondition(sql, params, ltq("name", "singno", "o").and(ltq("age", 1, "o")).or(ltq("sex", "MAN", "o")));
		System.out.println(sql.toString());
		System.out.println(params.toString());
	}
	
	@Test
	public void testLike() throws Exception
	{
		addFilterCondition(sql, params, like("name", "", "o").and(like("age", 1, "o")).or(like("sex", "MAN", "o")));
		addFilterCondition(sql, params, like("name", "singno", "o").and(like("age", 1, "o")).or(like("sex", "MAN", "o")));
		System.out.println(sql.toString());
		System.out.println(params.toString());
	}
	
	@Test
	public void testNotLike() throws Exception
	{
//		addFilterCondition(sql, params, notLike("name", "", "o").and(notLike("age", 1, "o")).or(notLike("sex", "MAN", "o")));
		addFilterCondition(sql, params, notLike("name", "singno", "o").and(notLike("age", 1, "o")).or(notLike("sex", "MAN", "o")));
		System.out.println(sql.toString());
		System.out.println(params.toString());
	}

	@Test
	public void testIsNull() throws Exception
	{
		addFilterCondition(sql, params, isNull("name", "o").and(isNull("age", "o")).or(isNull("sex", "o")));
		addFilterCondition(sql, params, isNull("name", "o").and(isNull("age", "o")).or(isNull("sex", "o")));
		System.out.println(sql.toString());
		System.out.println(params.toString());
	}
	
	@Test
	public void testIsNotNull() throws Exception
	{
		addFilterCondition(sql, params, isNotNull("name", "o").and(isNotNull("age", "o")).or(isNotNull("sex", "o")));
		System.out.println(sql.toString());
		System.out.println(params.toString());
	}

	@Test
	public void testBetween_and() throws Exception
	{
		addFilterCondition(sql, params, between_and("name", "A", "B", "o").and(between_and("name", "C", "d", "o")).or(between_and("name", "D", "E", "o")));
		System.out.println(sql.toString());
		System.out.println(params.toString());
	}
}
