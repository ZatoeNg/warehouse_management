package org.example.service;

import org.example.beans.InStore;
import org.example.beans.OutStore;
import org.example.beans.Result;
import org.example.beans.Transshipment;
import org.example.page.Page;


public interface TransshipmentService {

// 添加调货
    public Result transshipmentAdd(Transshipment transshipment);

//    查询调货单列表
    Page queryTransPage(Page page, Transshipment transshipment);

//    确认入库
    Result setTransState(Transshipment transshipment);
}
