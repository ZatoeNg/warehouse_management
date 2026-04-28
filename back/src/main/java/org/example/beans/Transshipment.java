package org.example.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transshipment implements Serializable {

    //    调货单ID
    private Integer transshipmentId;
    //    库存量
    private Integer productInvent;
    //    商品名称
    private String productName;
    //    源商品ID
    private Integer sourceProductId;
    //    源仓库ID
    private Integer sourceStoreId;
    //    目标商品编码
    private String targetProductNum;
    //    目标仓库ID
    private Integer targetStoreId;
    //    调库量
    private Integer transNum;

    //    附加
//    目标商品名
    private String targetProductName;

    //    源商品名
    private String sourceProductName;

    //    创建人
    private Integer createBy;

    //    调货状态
    private String transState;
    //    调货人
    private Integer transBy;
    //    调货时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date transDate;

    //    调货人
    private String createName;

    private String userCode;

    //    目标商品名
    private String targetStoreName;

    //    源商品名
    private String sourceStoreName;

    //    商品价格
    private BigDecimal salePrice;

    private Integer productId;
    private static final long serialVersionUID = 1L;

}
