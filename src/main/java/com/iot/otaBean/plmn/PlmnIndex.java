package com.iot.otaBean.plmn;

import java.io.Serializable;

public class PlmnIndex implements Serializable {

    private static final long serialVersionUID = 5702070796404428751L;
    private String mcc;
    private String plmn;
    private String plmnIndex;
    public String getMcc() {
        return mcc;
    }
    public void setMcc(String mcc) {
        this.mcc = mcc;
    }
    public String getPlmn() {
        return plmn;
    }
    public void setPlmn(String plmn) {
        this.plmn = plmn;
    }
    public String getPlmnIndex() {
        return plmnIndex;
    }
    public void setPlmnIndex(String plmnIndex) {
        this.plmnIndex = plmnIndex;
    }

}