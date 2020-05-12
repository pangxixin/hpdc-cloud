package com.hpdc.iface.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderDataheader implements Serializable {
    private String carrier_id;
    private String client_id;
    private String customer_id;
    private String from_site_id;
    private String instructions;
    private String order_id;
    private String order_type;
    private String owner_id;
    private String service_level;
    private String status;
    private List<OrderDataline> datalines;
}
