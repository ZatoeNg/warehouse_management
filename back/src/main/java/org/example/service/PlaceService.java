package org.example.service;

import org.example.beans.Place;
import org.example.beans.Result;

import java.util.List;

public interface PlaceService{
//    查询所有场地
    public List<Place> queryAllPlace();

//    添加场地
    Result placeAdd(Place place);
}
