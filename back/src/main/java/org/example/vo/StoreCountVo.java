package org.example.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//封装仓库商品数量
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreCountVo {

    private Integer storeId;

    private String storeName;

    private Integer totalInvent;


}
