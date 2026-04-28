package org.example.service;

import org.example.beans.Brand;
import org.example.beans.Result;

import java.util.List;

public interface BrandService{

//    查询所有品牌
    public List<Brand> queryAllBrand();

    Result brandAdd(Brand brand);
}
