package com.hpdc.iface.pojo;

import lombok.Data;

@Data
public class ZhlbKH {
    private String faddressID;      //地址id
    private String faddressType;    //地址类型
    private int fcusID;             //客户ID
    private String fcusName;        //客户名称
    private String fempName;        //收货人
    private String ftel;            //收货电话
    private String fagentName;      //客户所在区域
    private String faddress;        //客户详细地址
    private String faddDate;        //最近修改客户信息时间
}
