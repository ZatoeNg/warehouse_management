package org.example.service.impl;

import org.example.beans.Product;
import org.example.beans.Result;
import org.example.page.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.example.mapper.ProductMapper;
import org.example.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Page queryProductPage(Page page, Product product) {

        Integer productRowCount = productMapper.findProductRowCount(product);

        List<Product> productPage = productMapper.findProductPage(page, product);

        page.setTotalNum(productRowCount);
        page.setResultList(productPage);

        return page;
    }

//    添加商品
    @Value("${file.access-path}")
    private String fileAccessPath;

    @Transactional
    @Override
    public Result saveProduct(Product product) {
        Product productByNumAndStoreId = productMapper.findProductByNumAndStoreId(product);

        if(productByNumAndStoreId!=null){
            return Result.err(Result.CODE_ERR_BUSINESS,"该仓库商品型号已存在");
        }

//        处理保存在数据库的图片路径
        String saveImgePathBySQL = fileAccessPath + product.getImgs();
        product.setImgs(saveImgePathBySQL);

        int i = productMapper.insertProduct(product);
        if (i>0) return Result.ok("商品添加成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"商品添加失败");
    }

    //    修改商品上下架状态
    @Override
    @Transactional
    public Result updateStateByPid(Product product) {
        int i = productMapper.setStateByPid(product.getProductId(), product.getUpDownState());
        if(i>0)return Result.ok("状态修改成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"状态修改失败");
    }

    @Override
    @Transactional
    public Result deleteProductByIdS(List<Integer> productIds) {
        int i = productMapper.removeProductByIds(productIds);
        if(i>0)return Result.ok("删除成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"删除失败");
    }

    @Override
    @Transactional
    public Result setProductById(Product product) {
//        判断型号是否存在
        Product productByNum = productMapper.findProductByNumAndStoreId(product);

//       判断型号是否为当前修改商品的型号
//        int productNameById = productMapper.findProductNameById(product);

//        校验
        if(productByNum!=null && !product.getProductId().equals(productByNum.getProductId())){
            return Result.err(Result.CODE_ERR_BUSINESS,"商品已存在");
        }

//        判断图片是否被修改
        if(!product.getImgs().contains(fileAccessPath)){
            product.setImgs(fileAccessPath+product.getImgs());
        }

//        修改商品
        int i = productMapper.setProductById(product);
        if(i>0) return Result.ok("修改成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }

    // 检验编号是否存在
    @Override
    public Result productNumCheck(Product product) {
        Product productByNum = productMapper.findProductByNumAndStoreId(product);

        //        校验
        if(productByNum!=null)
        {
            return Result.ok("商品已存在",product);
        }

        return Result.ok("商品不存在");
    }
}
