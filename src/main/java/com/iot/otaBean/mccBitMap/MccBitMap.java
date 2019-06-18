package com.iot.otaBean.mccBitMap;


import java.io.Serializable;

public class MccBitMap implements Serializable {

    private static final long serialVersionUID = 4248995208365957616L;
    private long rec_id;
    private String mcc;
    private String countryName;
    private String bitmapCode;

    public long getRec_id() {
        return rec_id;
    }

    public void setRec_id(long rec_id) {
        this.rec_id = rec_id;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getBitmapCode() {
        return bitmapCode;
    }

    public void setBitmapCode(String bitmapCode) {
        this.bitmapCode = bitmapCode;
    }

}

