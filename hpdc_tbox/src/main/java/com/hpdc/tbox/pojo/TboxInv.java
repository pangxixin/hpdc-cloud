package com.hpdc.tbox.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "HPDC_TBOX_INVENTORY", schema = "HYWMS")
public class TboxInv implements Serializable {
    @Id
    private String tboxid;
    private String tboxDesc;
    private String tboxType;
    private Date tboxStartDate;
    private String tboxState;
    private String tboxInout;
    private Date tboxInTime;
    private Date tboxOutTime;
    private String tboxOutCustomer;
    private String tboxNotes;
}
