package cn.singno.commonsframework.dao;

import java.util.List;

import cn.singno.commonsframework.entity.Class;

public interface ClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
    
    List<Class> selectByName(String name);
    
    void insertList(List<Class> list);
}