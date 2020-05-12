package com.hpdc.iface.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDataline implements Serializable {
    private String back_ordered;
    private String batch_id;
    private String client_id;
    private String condition_id;
    private int line_id;
    private String lock_code;
    private String notes;
    private String order_id;
    private String origin_id;
    private String owner_id;
    private int qty_orderd;
    private String sku_id;
    private String user_def_type_8;
}
