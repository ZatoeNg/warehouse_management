package org.example.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory implements Serializable {

    private Integer inventoryId;

    private Integer storeId;

    private Integer productId;

    private Integer sourceNum;

    private Integer inventoryNum;

    private Integer inventoryBy;

    private String inventoryState;

    private Integer createBy;

    @JsonFormat(pattern ="yyyy-MM-dd" )
    private Date inventoryTime;

    @JsonFormat(pattern ="yyyy-MM-dd" )
    private Date createTime;

    private String userCode;
    private String productName;
    private String StoreName;

    private static final long serialVersionUID = 1L;
}
