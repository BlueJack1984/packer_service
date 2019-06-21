package com.iot.otaBean.AAA;

import com.iot.otaBean.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 记录实体
 * @author lushusheng
 * @date 2019-06-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AAA extends BaseModel {

    /**
     *主号
     */
    private String iccid;
    /**
     *落地国家码
     */
    private String mcc;
}
