package org.example.service.impl;

import org.example.beans.*;
import org.example.mapper.InStoreMapper;
import org.example.mapper.OutStoreMapper;
import org.example.mapper.ProductMapper;
import org.example.mapper.TransshipmentMapper;
import org.example.page.Page;
import org.example.service.TransshipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransshipmentServiceImpl implements TransshipmentService {

    @Autowired
    private TransshipmentMapper transshipmentMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Result transshipmentAdd(Transshipment transshipment) {

        Product pd = transshipmentMapper.CheckStoreProduct(transshipment);
        if (pd == null) {
            Product product = transshipmentMapper.findProductById(transshipment.getSourceProductId());
            product.setProductId(null);
            product.setProductInvent(0);
            product.setStoreId(transshipment.getTargetStoreId());
            product.setCreateBy(transshipment.getCreateBy());
            product.setCreateTime(null);
            int i = productMapper.insertProduct(product);
            if (i > 0) ;
            else return Result.err(Result.CODE_ERR_BUSINESS, "商品添加失败");
        }
        int i = transshipmentMapper.saveTrans(transshipment);
        if (i > 0) return Result.ok("成功");
        else return Result.err(Result.CODE_ERR_BUSINESS, "失败");
    }

    @Override
    public Page queryTransPage(Page page, Transshipment transshipment) {
        Integer transRowCount = transshipmentMapper.findTransMapperRowCount(transshipment);

        List<Transshipment> transPage = transshipmentMapper.findTransPage(page, transshipment);

        for (Transshipment trans : transPage) {
            String outStoreSourceNameById = transshipmentMapper.findStoreNameById(trans.getSourceStoreId());
            trans.setSourceStoreName(outStoreSourceNameById);

            String outStoreTargetNameById = transshipmentMapper.findStoreNameById(trans.getTargetStoreId());
            trans.setTargetStoreName(outStoreTargetNameById);
        }

        page.setTotalNum(transRowCount);
        page.setResultList(transPage);
        return page;
    }

    @Autowired
    private OutStoreMapper outStoreMapper;
    @Autowired
    private InStoreMapper inStoreMapper;


    @Override
    public Result setTransState(Transshipment trans) {

        OutStore outStore = new OutStore();
        InStore inStore = new InStore();
        int x = transshipmentMapper.setTransState(trans);
        if (x > 0) {
            outStore.setTallyId(trans.getCreateBy());
            outStore.setCreateBy(trans.getCreateBy());
            inStore.setCreateBy(trans.getCreateBy());
            trans = transshipmentMapper.findTransById(trans);
            outStore.setProductId(trans.getProductId());
            outStore.setStoreId(trans.getSourceStoreId());
            outStore.setSalePrice(trans.getSalePrice());
            outStore.setOutNum(trans.getTransNum());
            System.out.println(outStore);
            int y = outStoreMapper.insertOutStore(outStore);
            if (y > 0);
            else Result.err(Result.CODE_ERR_BUSINESS, "失败1");



            trans = transshipmentMapper.findTransByIds(trans);
            inStore.setProductId(trans.getProductId());
            inStore.setStoreId(trans.getTargetStoreId());
            inStore.setInNum(trans.getTransNum());
            int z = inStoreMapper.insertInStore(inStore);
            if (z > 0) return Result.ok("成功");

        }
        return Result.err(Result.CODE_ERR_BUSINESS, "失败2");
    }
}
