package com.hpdc.inventory.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "HPDC_INVENTORY", schema = "HYWMS")
@Data
public class HpdcInventory implements Serializable {

    @Id
    @Column(name = "key")
    private Integer key;

    @Column(name = "sku_id")
    private String skuId;

    @Column(name = "description")
    private String desc;

    @Column(name = "location_id")
    private String locationId;

    @Column(name = "qty_on_hand")
    private int qtyOnHand;

}
