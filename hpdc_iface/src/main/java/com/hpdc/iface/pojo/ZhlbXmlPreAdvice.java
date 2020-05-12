package com.hpdc.iface.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ZhlbXmlPreAdvice {
    private Date fdate;
    private String foutNumber;
    private String fstock;
    private String ffhCompany;
    private String fcarriers;
    private int fcusID;
    private String fcusNumber;
    private String fshUnit;
    private String fshAddress;
    private String fnote;
    private String fbatchNumber;
    private String fspecs;
    private String fdrugName;
    private String fdrugNumber;
    private int fqty;
    private int fbigQty;
    private int flt;
}
