package org.example.controller;

import org.example.beans.*;
import org.example.page.Page;
import org.example.service.BrandService;
import org.example.service.ProductService;
import org.example.service.StoreService;
import org.example.service.TransshipmentService;
import org.example.utils.TokenUtils;
import org.example.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transshipment")
public class TransshipmentController {


    //    分页查询商品
    @Autowired
    private ProductService productService;

    @RequestMapping("/product-page-list")
    public Result productCategoryTree(Page page, Product product){

        page = productService.queryProductPage(page, product);

        return Result.ok(page);
    }

    @Autowired
    private StoreService storeService;
    //    查询所有仓库
    @RequestMapping("/store-list")
    public Result storeList(){

        List<Store> store = storeService.queryAllStore();
        return Result.ok(store);

    }

    //    查询所有品牌
    @Autowired
    private BrandService brandService;
    @RequestMapping("/brand-list")
    public Result brandList(){
        List<Brand> brands = brandService.queryAllBrand();
        return Result.ok(brands);
    }

    @RequestMapping("/product-num-check")
    public Result productNumCheck(Product product){
        return productService.productNumCheck(product);
    }

    @Autowired
    private TransshipmentService transshipmentService;
    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping("/transshipment-add")
    public Result transshipmentAdd(@RequestBody Transshipment transshipment,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token)
    {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        transshipment.setCreateBy(userId);
        return transshipmentService.transshipmentAdd(transshipment);
    }

    @RequestMapping("/transshipment-page-list")
    public Result transshipmentPageList(Page page,Transshipment transshipment){

        page = transshipmentService.queryTransPage(page, transshipment);
        return Result.ok(page);
    }

    @RequestMapping("/transshipment-confirm")
    public Result transshipmentConfirm(@RequestBody Transshipment transshipment,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token)
    {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        transshipment.setCreateBy(userId);
        return transshipmentService.setTransState(transshipment);
    }
}
