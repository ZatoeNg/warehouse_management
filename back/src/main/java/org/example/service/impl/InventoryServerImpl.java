package org.example.service.impl;

import org.example.beans.Inventory;
import org.example.beans.Result;
import org.example.mapper.InventoryMapper;
import org.example.page.Page;
import org.example.service.InventoryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServerImpl implements InventoryServer {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public Page queryInventoryPage(Page page, Inventory inv) {

        Integer invRowCount = inventoryMapper.findInvRowCount(inv);

        List<Inventory> invPage = inventoryMapper.findInvPage(page, inv);

        page.setTotalNum(invRowCount);
        page.setResultList(invPage);

        return page;
    }

    @Override
    public Result updateInv(Inventory inv) {

        Integer inventoryNum = inv.getInventoryNum();
        Integer sourceNum = inv.getSourceNum();
        Integer num = sourceNum - inventoryNum;

        if (num > 0) inv.setInventoryState("1");
        else if (num < 0) inv.setInventoryState("2");
        else inv.setInventoryState("3");

        int i = inventoryMapper.setNumById(inv);
        if (i > 0) {
            int x = inventoryMapper.setProductInventById(inv);
            if (x > 0) return Result.ok("成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "失败");
    }

    @Override
    public Result addInv(Inventory inv) {
        int i = inventoryMapper.addInv(inv);
        if (i > 0)return Result.ok("成功");
        return Result.err(Result.CODE_ERR_BUSINESS, "失败");
    }

    @Override
    public Result deleteInv(int inventoryId) {

        int i = inventoryMapper.deleteInv(inventoryId);
        if (i > 0)return Result.ok("成功");
        return Result.err(Result.CODE_ERR_BUSINESS, "失败");
    }
}
