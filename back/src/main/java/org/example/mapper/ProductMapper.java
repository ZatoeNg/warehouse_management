package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.Product;
import org.example.page.Page;

import java.util.List;

public interface ProductMapper {

//    查询商品行数
    public Integer findProductRowCount(Product product);

//    分页查询商品
    public List<Product> findProductPage(@Param("page") Page page,@Param("product") Product product);

//    添加商品
    public int insertProduct(Product product);

//    根据型号查询商品
    public Product findProductByNum(String productNum);

//    根据商品id修改上下架状态
    public int setStateByPid(Integer productId,String upDownState);

//    根据id删除商品
    public int removeProductByIds(List<Integer> ProductList);

//    根据商品id修改商品
    public int setProductById(Product product);

//    根据id修改商品库存
    public int setInventById(Integer productId,Integer invent);

//    根据商品id查出商品库存
    public int findInventById(Integer productId);

    //    根据型号和仓库查询商品
    Product findProductByNumAndStoreId(@Param("product")Product product);
}