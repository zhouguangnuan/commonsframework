package cn.singno.commonsframework.dao;

import java.util.List;

import cn.singno.commonsframework.entity.Coures;

public interface CouresMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Coures record);

    int insertSelective(Coures record);

    Coures selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coures record);

    int updateByPrimaryKey(Coures record);
    
    void insertList(List<Coures> list);
    
    List<Coures> selectAll();
    
    List<Coures> selectByTeacherId(Integer teacherId);
}