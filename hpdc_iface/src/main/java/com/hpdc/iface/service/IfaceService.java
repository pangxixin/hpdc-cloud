package com.hpdc.iface.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hpdc.iface.constants.InterfaceConstants;
import com.hpdc.iface.mapper.IfaceMapper;
import com.hpdc.iface.pojo.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class IfaceService {

    @Resource
    IfaceMapper ifaceMapper;

    //===联邦接口====================================================================================
    // 获取联邦Token
    private static JSONObject getZhlbToken() {
        try {
            PostMethod postMethod = new PostMethod(InterfaceConstants.urlZhlbGetToken);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            //参数设置，需要注意的就是里边不能传NULL，要传空字符串
            NameValuePair[] data = {
                    new NameValuePair("appKey", "DDoZsKMBIkmmeyFm"),
                    new NameValuePair("appSecret", "v4qDlDlMxBh2tg3gG5TjfqBBR0JTG6oF")
            };
            postMethod.setRequestBody(data);
            HttpClient httpClient = new HttpClient();
            httpClient.executeMethod(postMethod); // 执行POST方法
            String result = postMethod.getResponseBodyAsString();

            return JSONObject.parseObject(result);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // 根据获取到的token，得到不同连接的数据信息
    private static JSONObject getZhlbData(String urlStr, String varToken) {
        try {
            GetMethod getMethod = new GetMethod(urlStr);
            getMethod.addRequestHeader("auth", varToken);
            getMethod.setRequestHeader("Content-Type", "application/json;charset=utf-8");
            HttpClient httpClient = new HttpClient();
            httpClient.executeMethod(getMethod); // 执行GET方法
            String result = getMethod.getResponseBodyAsString();
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //获取联邦SKU信息
    public List<ZhlbSKU> getZhlbSkuData() {
        // 1、获取token
        String zhlbToken = getZhlbToken().getString("Data");
        // 2、获取数据
        JSONObject result = getZhlbData(InterfaceConstants.urlZhlbGetMtr, zhlbToken);
        // 3、取得data数据转成jsonarray
        JSONArray resultJSONArray = result.getJSONArray("Data");
        // 4、将jsonarray转成ZhlbSP实体list
        return resultJSONArray.toJavaList(ZhlbSKU.class);
    }

    // 获取联邦客户信息
    public List<ZhlbKH> getZhlbKhData() {
        String zhlbToken = getZhlbToken().getString("Data");
        JSONObject result = getZhlbData(InterfaceConstants.urlZhlbGetCusMessage, zhlbToken);
        JSONArray resultJSONArray = result.getJSONArray("Data");
        // 4、将jsonarray转成ZhlbSP实体list并返回
        return resultJSONArray.toJavaList(ZhlbKH.class);
    }

    // 获取联邦入库信息
    public List<ZhlbRK> getZhlbRkData() {
        String zhlbToken = getZhlbToken().getString("Data");
        JSONObject result = getZhlbData(InterfaceConstants.urlZhlbGetINStock, zhlbToken);
        JSONArray resultJSONArray = result.getJSONArray("Data");
        return resultJSONArray.toJavaList(ZhlbRK.class);
    }

    // 获取联邦出库信息
    public List<ZhlbCK> getZhlbCkData() {
        String zhlbToken = getZhlbToken().getString("Data");
        JSONObject result = getZhlbData(InterfaceConstants.urlZhlbGetOutStock, zhlbToken);
        JSONArray resultJSONArray = result.getJSONArray("Data");
        return resultJSONArray.toJavaList(ZhlbCK.class);
    }

    // 获取联邦出库信息并生成interfaceOrder数据
    private InterfaceOrderData generateZhlbInterfaceOrderData() {
        List<ZhlbCK> ckData = getZhlbCkData();
        if (ckData != null) {
            // 遍历出库单List,转成orderheader和orderline
            List<InterfaceOrderHeader> interfaceOrderHeaderList = new ArrayList<>();
            List<InterfaceOrderLine> interfaceOrderLineList = new ArrayList<>();
            for (ZhlbCK zhlbCK : ckData) {
                // 生成订单头数据
                InterfaceOrderHeader interfaceOrderHeader = new InterfaceOrderHeader();
                interfaceOrderHeader.setOrderId(zhlbCK.getFoutNumber()); //设置出库单号

                String faddressType = zhlbCK.getFaddressType();
                if ("1".equals(faddressType) || faddressType.isEmpty()) {
                    interfaceOrderHeader.setCustomerId(zhlbCK.getFcusNumber()); //设置客户号
                    interfaceOrderHeader.setCarrierId(getCarrier(zhlbCK.getFcusNumber())); //设置配送方式
                } else {
                    interfaceOrderHeader.setCustomerId(zhlbCK.getFcusNumber() + "-" + faddressType);
                    interfaceOrderHeader.setCarrierId(getCarrier(zhlbCK.getFcusNumber() + "-" + faddressType));
                }

                String ownerId = getOwnerId(zhlbCK.getFfhCompany()); //设置货主
                interfaceOrderHeader.setOwnerId(ownerId);
                interfaceOrderHeaderList.add(interfaceOrderHeader);
                // 生成订单行数据
                String skuId = getSkuId(ownerId, zhlbCK.getFdrugNumber());
                InterfaceOrderLine interfaceOrderLine = new InterfaceOrderLine();
                interfaceOrderLine.setOrderId(zhlbCK.getFoutNumber());
                interfaceOrderLine.setUdn2(zhlbCK.getFoutNumber());
                interfaceOrderLine.setSkuId(skuId);
                interfaceOrderLine.setQtyOrdered(String.valueOf(zhlbCK.getFqty()));
                interfaceOrderLine.setUdt8(getUdt8(skuId, zhlbCK.getFbatchNumber().toUpperCase()));
                interfaceOrderLine.setOwnerId(ownerId);
                interfaceOrderLine.setLineId(String.valueOf(zhlbCK.getFrowNum()));
                interfaceOrderLineList.add(interfaceOrderLine);
            }
            InterfaceOrderData interfaceOrderData = new InterfaceOrderData();
            interfaceOrderData.setOrderHeaders(interfaceOrderHeaderList);
            interfaceOrderData.setOrderLines(interfaceOrderLineList);

            return interfaceOrderData;
        } else {
            return null;
        }
    }

    // 将zhlb出库单直接插入数据库接口表
    public void importZhlbCkdToDB() {
        InterfaceOrderData zhlbInterfaceOrderData = generateZhlbInterfaceOrderData();
        // 订单头去重后保存
        assert zhlbInterfaceOrderData != null;
        zhlbInterfaceOrderData.getOrderHeaders()
                .stream()
                .distinct()
                .collect(Collectors.toList())
//                .forEach(ifaceMapper::insertZhlbInterfaceOrderHeader);
                .forEach(System.out::println);
        // 保存所有订单行
        zhlbInterfaceOrderData.getOrderLines()
//                .forEach(ifaceMapper::insertZhlbInterfaceOrderLine);
                .forEach(System.out::println);
        // 检查是否有notag的订单,有则提示
        /*List<InterfaceOrderLine> orderLineList = zhlbInterfaceOrderData.getOrderLines();
        for (InterfaceOrderLine interfaceOrderLine : orderLineList) {
            if ("notag".equals(interfaceOrderLine.getUdt8())) {
                throw new Result(false, StatusCode.ERROR, "notag");
            }
        }*/
    }

    // 获取联邦入库信息并生成interfacePreAdvice数据
    private InterfacePreAdviceData generateZhlbInterfacePreAdviceData() {
        List<ZhlbRK> rkData = getZhlbRkData();
        if (rkData != null) {
            // 遍历入库单List，转成preAdviceheader和preAdviceline
            List<InterfacePreAdviceHeader> interfacePreAdviceHeaderList = new ArrayList<>();
            List<InterfacePreAdviceLine> interfacePreAdviceLineList = new ArrayList<>();
            for (ZhlbRK zhlbRK : rkData) {
                String preAdviceId = zhlbRK.getFoutNumber() + "-" + zhlbRK.getFrowNum();
                String ownerId = getOwnerId(zhlbRK.getFfhCompany());
                // 生成预通知单头数据
                InterfacePreAdviceHeader interfacePreAdviceHeader = new InterfacePreAdviceHeader();
                interfacePreAdviceHeader.setPreAdviceId(preAdviceId);
                interfacePreAdviceHeader.setOwnerId(ownerId);
                interfacePreAdviceHeaderList.add(interfacePreAdviceHeader);
                // 生成预通知单行数据
                String skuId = getSkuId(ownerId, zhlbRK.getFdrugNumber());
                InterfacePreAdviceLine interfacePreAdviceLine = new InterfacePreAdviceLine();
                interfacePreAdviceLine.setPreAdviceId(preAdviceId);
                interfacePreAdviceLine.setSkuId(skuId);
                interfacePreAdviceLine.setBatchId(zhlbRK.getFbatchNumber());
                interfacePreAdviceLine.setUdn1(zhlbRK.getFbatchNumber());
                interfacePreAdviceLine.setQtyDue(zhlbRK.getFqty());
                interfacePreAdviceLine.setOwnerId(ownerId);
                interfacePreAdviceLineList.add(interfacePreAdviceLine);
            }
            InterfacePreAdviceData interfacePreAdviceData = new InterfacePreAdviceData();
            interfacePreAdviceData.setPreAdviceHeaders(interfacePreAdviceHeaderList);
            interfacePreAdviceData.setPreAdviceLines(interfacePreAdviceLineList);
            return interfacePreAdviceData;
        } else {
            return null;
        }
    }

    // 将zhlb入库单直接插入数据库接口表
    public void importZhlbRkdToDB() {
        InterfacePreAdviceData interfacePreAdviceData = generateZhlbInterfacePreAdviceData();
        assert interfacePreAdviceData != null;
        interfacePreAdviceData.getPreAdviceHeaders()
//                .forEach(ifaceMapper::insertZhlbInterfacePreAdviceHeader);
                .forEach(System.out::println);
        interfacePreAdviceData.getPreAdviceLines()
//                .forEach(ifaceMapper::insertZhlbInterfacePreAdviceLine);
                .forEach(System.out::println);
    }

    //获取user_def_type_8
    private String getUdt8(String skuId, String batchId) {
        return ifaceMapper.getUdt8(skuId, batchId);
    }

    //通过客户地址查出配送方式
    private String getCarrier(String addressId) {
        return ifaceMapper.getCarrier(addressId);
    }

    //根据联邦公司名称设置ownerid
    private String getOwnerId(String ffhCompany) {
        if ("中山分公司".equals(ffhCompany)) {
            return "ZSLB";
        } else if ("联邦".equals(ffhCompany)) {
            return "ZHLB";
        } else if ("万邦".equals(ffhCompany)) {
            return "WBLB";
        }
        return "XXLB";
    }

    // 万邦skuid前加WB
    private String getSkuId(String ownerId, String fdrugNumber) {
        if ("WBLB".equals(ownerId)) {
            return "WB" + fdrugNumber;
        } else {
            return fdrugNumber;
        }
    }
}
