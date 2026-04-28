package org.example.service;

import org.example.beans.Result;
import org.example.beans.Unit;

import java.util.List;

public interface UnitService{

//    查询所有单位
    public List<Unit> queryAllUnit();

//    添加单位
    Result unitAdd(Unit unit);
}
