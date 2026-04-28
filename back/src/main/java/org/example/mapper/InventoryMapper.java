package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.beans.Inventory;
import org.example.page.Page;

import java.util.List;

public interface InventoryMapper {

    public Integer findInvRowCount(@Param("inv")Inventory inv);

    public List<Inventory> findInvPage(@Param("page")Page page,@Param("inv")  Inventory inv);

    public int setNumById(@Param("inv") Inventory inv);

    public int addInv(@Param("inv") Inventory inv);

    public int setProductInventById(@Param("inv")Inventory inv);

    public int deleteInv(int inventoryId);
}
