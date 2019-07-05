package com.iot.otaBean.preStartOrderRecord;

import com.iot.otaBean.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 记录实体
 * @author lushusheng
 * @date 2019-06-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PreStartOrderRecord extends BaseModel {

    /**
     *主号
     */
    private String iccid;
    /**
     *主号imsi
     */
    private String imsi;
    /**
     *订单主键id
     */
    private String orderId;
    /**
     *副号imsi
     */
    private String accessoryImsi;
    /**
     *订单开始时间
     */
    private String startTime;
    /**
     *订单预计结束时间
     */
    private String expectedFinishTime;

}
