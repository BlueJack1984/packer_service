package com.iot.service.interfaces;

/**
 *
 */
public interface PreStartOrderService {
    /**
     * 插入一条预启动订单数据
     * @param iccid
     * @param imsi
     * @param orderId
     * @param accessoryImsi
     * @param expectedFinishTime
     */
    void insert(String iccid, String imsi, String orderId, String accessoryImsi, String expectedFinishTime);
}
