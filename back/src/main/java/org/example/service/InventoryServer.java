package org.example.service;

import org.example.beans.Inventory;
import org.example.beans.Result;
import org.example.page.Page;

public interface InventoryServer {

    public Page queryInventoryPage(Page page, Inventory inv);

    public Result updateInv(Inventory inv);

    public Result addInv(Inventory inv);

    public Result deleteInv(int inventoryId);
}
