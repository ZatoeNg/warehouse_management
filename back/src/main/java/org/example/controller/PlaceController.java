package org.example.controller;

import org.example.beans.Place;
import org.example.beans.Result;
import org.example.beans.Unit;
import org.example.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping("/place-add")
    public Result placeAdd(@RequestBody Place place){
        Result result = placeService.placeAdd(place);
        return result;
    }
}
