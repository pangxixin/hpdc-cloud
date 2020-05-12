package com.hpdc.iface.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ZhlbRK {
    private Date fdate;             //单据日期
    private String foutNumber;      //出库单号
    private int frowNum;            //行号
    private String fstock;          //仓库
    private String ffhCompany;      //公司
    private String fcarriers;       //承运商
    private String fshTel;          //收货电话
    private String fcusNumber;      //客户编码
    private String fshUnit;         //收货单位
    private String fshAddress;      //收货地址
    private String fshName;         //收货人
    private String fnote;           //备注
    private String fbatchNumber;    //批号
    private String fspecs;          //规格
    private String fspecs1;          //规格1
    private String fdrugName;       //药品名称
    private String fdrugNumber;     //药品编码
    private String fmanufacturer;   //生产厂家
    private String fdosage;         //剂型
    private String fapprovalNumber; //批准文号
    private String funit;           //单位
    private Date fscqDate;          //生产日期
    private Date fyxqDate;          //有效期
    private int fqty;               //数量
    private int fbigQty;            //大件数
    private int flt;                //零头
    private Double fweight;         //重量
    private String fproductName;    //产品名称
}
