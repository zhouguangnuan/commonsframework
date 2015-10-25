package cn.singno.commonsframework.dao;

import java.util.List;

import cn.singno.commonsframework.entity.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    List<Student> selectByName(String name);
    
    void insertList(List<Student> list);
    
    List<Student> selectAll();
}