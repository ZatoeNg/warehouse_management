package org.example.controller;


import org.example.beans.*;
import org.example.page.Page;
import org.example.service.*;
import org.example.utils.TokenUtils;
import org.example.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    private StoreService storeService;

//    查询所有仓库
    @RequestMapping("/store-list")
    public Result storeList(){
        List<Store> stores = storeService.queryAllStore();

        return Result.ok((stores));
    }

//    查询所有品牌
    @Autowired
    private BrandService brandService;
    @RequestMapping("/brand-list")
    public Result brandList(){
        List<Brand> brands = brandService.queryAllBrand();

        return Result.ok(brands);
    }

//    分页查询商品
    @Autowired
    private ProductService productService;
    @RequestMapping("/product-page-list")
    public Result productListpage(Page page, Product product){

        page = productService.queryProductPage(page, product);

        return Result.ok(page);
    }

//    查询所有商品分类树
    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/category-tree")
    public Result loadTypeTree(){
        List<ProductType> productTypes = productTypeService.productTyprTree();

        return Result.ok(productTypes);
    }

//    查询所有供应商
    @Autowired
    private SupplyService supplyService;
    @RequestMapping("/supply-list")
    public Result supplyList(){
        List<Supply> supplies = supplyService.queryAllSupply();

        return Result.ok(supplies);
    }

//    查询所有产地
    @Autowired
    private PlaceService placeService;
    @RequestMapping("/place-list")
    public Result placeList(){
        List<Place> places = placeService.queryAllPlace();

        return Result.ok(places);
    }

//    查询所有单位
    @Autowired
    private UnitService unitService;

    @RequestMapping("/unit-list")
    public Result unitList(){
        List<Unit> units = unitService.queryAllUnit();

        return Result.ok(units);
    }

//    上传图片
    @Value("${file.upload-path}")

    private String uploadPath;

    @CrossOrigin //表示”/img-upload“url允许跨域请求
    @RequestMapping("/img-upload")
    public Result uploadImge(@RequestBody MultipartFile file){

        try {
//            ResourceUtils的方法getFile，解析类路径
            File uploadDirFile = ResourceUtils.getFile(uploadPath);
//            获取磁盘路径
            String uploadDirPath = uploadDirFile.getAbsolutePath();

//            拿到上传图片的名称
            String filename = file.getOriginalFilename();

//            保存的磁盘文件
            String uploadFilePath = uploadDirPath + "\\" + filename;

//            上传图片,transferTo(保存到的磁盘文件的File对象)
            file.transferTo(new File(uploadFilePath));

            return Result.ok("上传成功");

        } catch (Exception e) {
            return Result.err(Result.CODE_ERR_BUSINESS,"上传失败");
        }

    }

    @Autowired
    private TokenUtils tokenUtils;
//    添加商品
    @RequestMapping("/product-add")
    public Result addProduct(@RequestBody Product product,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();

        product.setCreateBy(userId);

        Result result = productService.saveProduct(product);

        return result;
    }

//    修改商品上下架
    @RequestMapping("/state-change")
    public Result changeProductState(@RequestBody Product product){
        Result result = productService.updateStateByPid(product);

        return result;
    }

//    删除商品
    @RequestMapping("/product-delete/{productId}")
    public Result deleteProduct(@PathVariable  Integer productId){
        Result result = productService.deleteProductByIdS(Arrays.asList(productId));

        return result;
    }

//    批量删除
    @RequestMapping("/product-list-delete")
    public Result deleteProductList(@RequestBody List<Integer> productIds){
        Result result = productService.deleteProductByIdS(productIds);

        return result;
    }

//    修改商品
    @RequestMapping("/product-update")
    public Result updateProduct(@RequestBody Product product,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){

        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        product.setUpdateBy(userId);

        Result result = productService.setProductById(product);

        return result;
    }
}
