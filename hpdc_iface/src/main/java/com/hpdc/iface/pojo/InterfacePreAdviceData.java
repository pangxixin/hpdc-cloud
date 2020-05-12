package com.hpdc.iface.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InterfacePreAdviceData implements Serializable {
    List<InterfacePreAdviceHeader>  preAdviceHeaders;
    List<InterfacePreAdviceLine> preAdviceLines;
}
