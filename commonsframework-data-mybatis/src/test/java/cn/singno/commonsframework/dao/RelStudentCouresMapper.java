package cn.singno.commonsframework.dao;

import java.util.List;

import cn.singno.commonsframework.entity.RelStudentCoures;

public interface RelStudentCouresMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RelStudentCoures record);

    int insertSelective(RelStudentCoures record);

    RelStudentCoures selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RelStudentCoures record);

    int updateByPrimaryKey(RelStudentCoures record);
    
    void insertList(List<RelStudentCoures> list);
}