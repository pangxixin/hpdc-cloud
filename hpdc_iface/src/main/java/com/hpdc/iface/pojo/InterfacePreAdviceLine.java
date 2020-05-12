package com.hpdc.iface.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InterfacePreAdviceLine implements Serializable {
    private int key;
    private String preAdviceId;
    private String skuId;
    private String batchId;
    private int qtyDue;
    private String ownerId;
    private String udn1;
}
