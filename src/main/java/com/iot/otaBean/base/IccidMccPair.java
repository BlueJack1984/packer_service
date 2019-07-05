package com.iot.otaBean.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据对
 * @author lushusheng
 * @date 2019-07-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IccidMccPair implements Serializable {

    private String iccid;

    private String mcc;
}
