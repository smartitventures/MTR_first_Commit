package mtr.Response.deviceidValidation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("driver_lname")
    @Expose
    private String driverLname;
    @SerializedName("domainname")
    @Expose
    private String domainname;
    @SerializedName("taxi_driver_info_id")
    @Expose
    private Integer taxiDriverInfoId;
    @SerializedName("domainid")
    @Expose
    private Integer domainid;
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverLname() {
        return driverLname;
    }

    public void setDriverLname(String driverLname) {
        this.driverLname = driverLname;
    }

    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }
    public Integer getTaxiDriverInfoId() {
        return taxiDriverInfoId;
    }

    public void setTaxiDriverInfoId(Integer taxiDriverInfoId) {
        this.taxiDriverInfoId = taxiDriverInfoId;
    }

    public Integer getDomainid() {
        return domainid;
    }

    public void setDomainid(Integer domainid) {
        this.domainid = domainid;
    }

}