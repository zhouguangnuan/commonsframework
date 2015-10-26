package cn.singno.commonsframework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.singno.commonsframework.bean.Page;
import cn.singno.commonsframework.entity.Student;
import cn.singno.commonsframework.model.SearchModel;

public interface StudentMapper
{
	int deleteByPrimaryKey(Integer id);

	int insert(Student record);

	int insertSelective(Student record);

	Student selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Student record);

	int updateByPrimaryKey(Student record);

	List<Student> selectByName(String name);

	void insertList(List<Student> list);

	List<Student> selectAll();

	List<Student> selectByPage(@Param("page") Page<Student> page, @Param("filter") Map<String, Object> filter);

	List<Student> selectByPage2(Page<Student> page);

	List<Student> selectByPage3(SearchModel searchModel);

	List<Student> selectByP(Page<Student> page);
}