package org.example.service;

import org.example.beans.Product;
import org.example.beans.Result;
import org.example.page.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService{


    //    分页查询商品
    public Page queryProductPage(Page page, Product product);

//    添加商品
    public Result saveProduct(Product product);

//    修改商品上下架状态
    public Result updateStateByPid(Product product);

//    删除商品
    public Result deleteProductByIdS(List<Integer> productIds);

//    修改商品
    public Result setProductById(Product product);

// 检验编号是否存在
    public Result productNumCheck(Product product);
}
