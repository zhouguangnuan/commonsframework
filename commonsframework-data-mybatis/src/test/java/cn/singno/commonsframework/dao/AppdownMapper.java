package cn.singno.commonsframework.dao;

import cn.singno.commonsframework.entity.Appdown;

public interface AppdownMapper {
    int deleteByPrimaryKey(Integer sysno);

    int insert(Appdown record);

    int insertSelective(Appdown record);

    Appdown selectByPrimaryKey(Integer sysno);

    int updateByPrimaryKeySelective(Appdown record);

    int updateByPrimaryKey(Appdown record);
}