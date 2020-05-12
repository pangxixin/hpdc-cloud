package com.hpdc.iface.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ZhlbXmlOrder {
    private Date fdate;             //单据日期
    private String foutNumber;      //出库单号
    private int frowNum;            //行号
    private String fstock;          //仓库
    private String ffhCompany;      //公司
    private String fcarriers;       //承运商
    private int fcusID;             //客户ID
    private String faddressID;      //地址ID
    private String faddressType;    //地址Type
    private String fcusNumber;      //客户编码
    private String fshUnit;         //收货单位
    private String fshAddress;      //收货地址
    private String fnote;           //备注
    private String fbatchNumber;    //批号
    private String fspecs;          //规格
    private String fdrugName;       //规格
    private String fdrugNumber;     //货品编码
    private int fqty;               //数量
    private int fbigQty;            //大件数
    private int flt;                //零头
}
