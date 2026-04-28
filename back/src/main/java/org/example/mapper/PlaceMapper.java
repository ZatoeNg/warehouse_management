package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.Place;

import java.util.List;

public interface PlaceMapper {
//    查询所有产地
    public List<Place> findAllPlace();

//    添加场地
    int placeAdd(@Param("place") Place place);
}