package com.hpdc.iface.pojo;

import lombok.Data;

@Data
public class ZhlbSKU {
    private String fmtrNumber;         //物料编码
    private String fmtrName;        //物料名称
    private String fmtrSpec;        //物料规格
    private String ffactory;        //公司名称
    private Double fgrossWeight;    //物料毛重
    private Double fLength;         //物料长度
    private Double fWidth;          //物料宽度
    private Double fHeight;         //物料高度
    private Double fSize;           //物料体积
    private String fdosageForm;     //物料剂型
}
