package org.example.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 出库单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutStore implements Serializable {
    private Integer outsId;

    private Integer productId;

    private Integer storeId;

    private Integer tallyId;

    private BigDecimal outPrice;

    private Integer outNum;

    private Integer createBy;

    @JsonFormat(pattern ="yyyy-MM-dd" )
    private Date createTime;

    /**
    * 0 否 1 是
    */
    private String isOut;

    private static final long serialVersionUID = 1L;

//    出库价格
    private BigDecimal salePrice;

    private String productName;

    private String startTime;

    private String endTime;

    private String storeName;

    private String userCode;

    private String tallyName;
}