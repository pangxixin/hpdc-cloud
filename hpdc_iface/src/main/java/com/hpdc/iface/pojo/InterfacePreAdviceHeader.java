package com.hpdc.iface.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InterfacePreAdviceHeader implements Serializable {
    private int key;
    private String preAdviceId;
    private String supplierId;
    private String clientId;
    private String ownerId;
    private String siteId;
    private String preAdviceType;
    private String udt6;
    private String status;
    private String mergeAction;
    private String mergeStatus;
}
