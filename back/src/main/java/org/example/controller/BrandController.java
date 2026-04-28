package org.example.controller;


import org.example.beans.Brand;
import org.example.beans.Result;
import org.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @RequestMapping("/brand-add")
    public Result brandAdd(@RequestBody Brand brand){
        Result result = brandService.brandAdd(brand);
        return result;
    }
}
