package com.hpdc.iface.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InterfaceOrderHeader implements Serializable {
    private int key;
    private String orderId;
    private String customerId;
    private String clientId;
    private String ownerId;
    private String fromSiteId;
    private String carrierId;
    private String orderType;
    private String serviceLevel;
    private String status;
    private String udt6;
    private String mergeAction;
    private String mergeStatus;
}
