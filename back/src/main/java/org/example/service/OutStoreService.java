package org.example.service;

import org.example.beans.OutStore;
import org.example.beans.Result;
import org.example.mapper.OutStoreMapper;
import org.example.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public interface OutStoreService{

//    添加出库
    public Result saveOutStore(OutStore outStore);

//    分页查询出库单
    public Page queryOutStorePage(Page page,OutStore outStore);

//    确认出库
    public Result outStoreConfirm(OutStore outStore);

}
