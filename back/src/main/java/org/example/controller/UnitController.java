package org.example.controller;

import org.example.beans.Result;
import org.example.beans.Store;
import org.example.beans.Unit;
import org.example.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/unit")
@RestController
public class UnitController {

    @Autowired
    private UnitService unitService;

    @RequestMapping("/unit-add")
    public Result unitAdd(@RequestBody Unit unit){
        Result result = unitService.unitAdd(unit);
        return result;
    }
}
