package com.hpdc.tbox.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "HPDC_TBOX_TYPE", schema = "HYWMS")
public class TboxType implements Serializable {
    @Id
    private String typeId;
    private String tboxColor;
    private Double tboxHeight;
    private Double tboxWidth;
}
