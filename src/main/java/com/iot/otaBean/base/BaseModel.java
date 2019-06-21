package com.iot.otaBean.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体
 * @author lushusheng
 * @date 2019-06-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel implements Serializable {

    /**
     *主键id
     */
    private Long id;
    /**
     *数据状态标志
     */
    private String status;
    /**
     *数据创建时间
     */
    private Date createTime;
    /**
     *数据修改时间
     */
    private Date updateTime;
}
