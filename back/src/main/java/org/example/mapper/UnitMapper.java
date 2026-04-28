package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.Unit;

import java.util.List;

public interface UnitMapper {

//    查询所有单位
    public List<Unit> findAllUnit();

//    添加单位
    int unitAdd(@Param("unit") Unit unit);
}