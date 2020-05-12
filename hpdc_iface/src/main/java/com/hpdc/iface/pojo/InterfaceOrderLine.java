package com.hpdc.iface.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InterfaceOrderLine implements Serializable {
    private int key;
    private String orderId;
    private String udn2;
    private String lineId;
    private String skuId;
    private String udt8;
    private String qtyOrdered;
    private String clientId;
    private String ownerId;
    private String udt6;
    private String mergeAction;
    private String mergeStatus;
    private String conditionId;
}
