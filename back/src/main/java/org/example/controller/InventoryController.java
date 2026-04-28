package org.example.controller;

import org.example.beans.CurrentUser;
import org.example.beans.Inventory;
import org.example.beans.Result;
import org.example.page.Page;
import org.example.service.InventoryServer;
import org.example.utils.TokenUtils;
import org.example.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryServer inventoryServer;

    @RequestMapping("/inventory-page-list")
    public Result inventoryPageList(Page page,Inventory inv) {

        page= inventoryServer.queryInventoryPage(page,inv);

        return Result.ok(page);
    }

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping("/inventory-update")
    public Result inventoryUpdate(@RequestBody Inventory inv, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {

        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        inv.setInventoryBy(userId);

        return inventoryServer.updateInv(inv);
    }

    @RequestMapping("/inventory-add")
    public Result inventoryAdd(@RequestBody Inventory inv,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token)
    {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        inv.setCreateBy(userId);

        return inventoryServer.addInv(inv);
    }

    @RequestMapping("/inventory-delete/{inventoryId}")
    public Result inventoryDelete(@PathVariable int inventoryId) {

        return inventoryServer.deleteInv(inventoryId);
    }

}
