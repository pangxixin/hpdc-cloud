package com.hpdc.iface.mapper;

import com.hpdc.iface.pojo.InterfaceOrderHeader;
import com.hpdc.iface.pojo.InterfaceOrderLine;
import com.hpdc.iface.pojo.InterfacePreAdviceHeader;
import com.hpdc.iface.pojo.InterfacePreAdviceLine;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IfaceMapper {

    String getUdt8(String skuId, String batchId);

    String getCarrier(String addressId);

    void insertJtyxInterfaceOrderHeader(InterfaceOrderHeader orderHeader);

    void insertJtyxInterfaceOrderLine(InterfaceOrderLine orderLine);

    void insertZhlbInterfaceOrderHeader(InterfaceOrderHeader orderHeader);

    void insertZhlbInterfaceOrderLine(InterfaceOrderLine orderLine);

    void insertZhlbInterfacePreAdviceHeader(InterfacePreAdviceHeader preAdviceHeader);

    void insertZhlbInterfacePreAdviceLine(InterfacePreAdviceLine preAdviceLine);
}
