package com.hpdc.iface.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InterfaceOrderData implements Serializable {
    private List<InterfaceOrderHeader> orderHeaders;
    private List<InterfaceOrderLine> orderLines;
}
