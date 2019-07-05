package com.iot.otaBean.assetSoftsimUsage;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AssetSoftsimUsage implements Serializable {

    private Long id;

    private String assetId;

    private String iccid;

    private String imsi;

    private String msisdn;

    private String status;

    private String createDate;

    private String coverMcc;

    private String operatorCode;

    private String operatorName;

    private String supplierCode;

    private String supplierName;

    private String multiImsiFlag;

    private String softSimType;
}
