package cn.singno.commonsframework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.singno.commonsframework.entity.Teacher;
import cn.singno.commonsframework.model.TeacherModel;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    void insertList(List<Teacher> list);
    
    void deleteList(List<Teacher> list);
    
    void updateList(List<Teacher> list);
    
    List<Teacher> selectAll();
    
    TeacherModel selectModelByPrimaryKey(Integer id);
}