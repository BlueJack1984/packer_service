package com.iot.otaBean.locationUpdateInstruction;

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
public class LocationUpdateInstruction extends BaseModel {

    /**
     *主号
     */
    private String iccid;
    /**
     *落地国家码
     */
    private String mcc;
}
