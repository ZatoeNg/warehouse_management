package org.example.service;

import org.example.beans.InStore;
import org.example.beans.Result;
import org.example.page.Page;

public interface InStoreService{

//        添加入库单
    public Result saveInStore(InStore inStore,Integer buyId);
//    分页查询入库单
    public Page queryInStorePage(Page page,InStore inStore);

//    确认入库
    public Result confirmConfirm(InStore inStore);

}
