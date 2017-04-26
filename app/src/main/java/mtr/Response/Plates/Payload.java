package mtr.Response.Plates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("mak")
    @Expose
    private String mak;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("vyear")
    @Expose
    private Integer vyear;
    @SerializedName("noplate")
    @Expose
    private String noplate;

    public String getMak() {
        return mak;
    }

    public void setMak(String mak) {
        this.mak = mak;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getVyear() {
        return vyear;
    }

    public void setVyear(Integer vyear) {
        this.vyear = vyear;
    }

    public String getNoplate() {
        return noplate;
    }

    public void setNoplate(String noplate) {
        this.noplate = noplate;
    }

}