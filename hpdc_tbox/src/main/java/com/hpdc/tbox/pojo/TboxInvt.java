package com.hpdc.tbox.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "HPDC_TBOX_INVTRANSACTION", schema = "HYWMS")
public class TboxInvt implements Serializable {
    @Id
    private String key;
    private String tboxId;
    private String tboxOutCustomer;
    private String oprationAction;
    private Date oprationTime;
    private String userId;
}
