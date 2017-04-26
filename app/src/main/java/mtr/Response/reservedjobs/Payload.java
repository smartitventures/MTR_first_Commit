
package mtr.Response.reservedjobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("rsvrtime")
    @Expose
    private String rsvrtime;
    @SerializedName("reservationtime")
    @Expose
    private String reservationtime;
    @SerializedName("mtm_num")
    @Expose
    private String mtmNum;
    @SerializedName("tripno")
    @Expose
    private Integer tripno;
    @SerializedName("passanger_id")
    @Expose
    private String passangerId;
    @SerializedName("IsReserved")
    @Expose
    private Integer isReserved;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("pickupaddress")
    @Expose
    private String pickupaddress;
    @SerializedName("GroupId")
    @Expose
    private String groupId;
    @SerializedName("domainid")
    @Expose
    private Integer domainid;
    @SerializedName("DropAddress")
    @Expose
    private String dropAddress;
    @SerializedName("noofpassanger")
    @Expose
    private String noofpassanger;
    @SerializedName("colorcode")
    @Expose
    private String colorcode;
    public String getRsvrtime() {
        return rsvrtime;
    }

    public void setRsvrtime(String rsvrtime) {
        this.rsvrtime = rsvrtime;
    }

    public String getReservationtime() {
        return reservationtime;
    }

    public void setReservationtime(String reservationtime) {
        this.reservationtime = reservationtime;
    }

    public String getMtmNum() {
        return mtmNum;
    }

    public void setMtmNum(String mtmNum) {
        this.mtmNum = mtmNum;
    }

    public Integer getTripno() {
        return tripno;
    }

    public void setTripno(Integer tripno) {
        this.tripno = tripno;
    }

    public String getPassangerId() {
        return passangerId;
    }

    public void setPassangerId(String passangerId) {
        this.passangerId = passangerId;
    }

    public Integer getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(Integer isReserved) {
        this.isReserved = isReserved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPickupaddress() {
        return pickupaddress;
    }

    public void setPickupaddress(String pickupaddress) {
        this.pickupaddress = pickupaddress;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getDomainid() {
        return domainid;
    }

    public void setDomainid(Integer domainid) {
        this.domainid = domainid;
    }

    public String getDropAddress() {
        return dropAddress;
    }

    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }

    public String getNoofpassanger() {
        return noofpassanger;
    }

    public void setNoofpassanger(String noofpassanger) {
        this.noofpassanger = noofpassanger;
    }

    public String getColorcode() {
        return colorcode;
    }

    public void setColorcode(String colorcode) {
        this.colorcode = colorcode;
    }
}