package mtr.Response.regularjobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("smoke")
    @Expose
    private Integer smoke;
    @SerializedName("dialysis")
    @Expose
    private Integer dialysis;
    @SerializedName("AllowPets")
    @Expose
    private Integer allowPets;
    @SerializedName("AllowChilds")
    @Expose
    private Integer allowChilds;
    @SerializedName("AllowCats")
    @Expose
    private Integer allowCats;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("pickupaddress")
    @Expose
    private String pickupaddress;
    @SerializedName("noofpassanger")
    @Expose
    private String noofpassanger;
    @SerializedName("DropAddress")
    @Expose
    private String dropAddress;
    @SerializedName("pickupbetween")
    @Expose
    private String pickupbetween;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("time")
    @Expose
    private String time;

    public Integer getSmoke() {
        return smoke;
    }

    public void setSmoke(Integer smoke) {
        this.smoke = smoke;
    }

    public Integer getDialysis() {
        return dialysis;
    }

    public void setDialysis(Integer dialysis) {
        this.dialysis = dialysis;
    }

    public Integer getAllowPets() {
        return allowPets;
    }

    public void setAllowPets(Integer allowPets) {
        this.allowPets = allowPets;
    }

    public Integer getAllowChilds() {
        return allowChilds;
    }

    public void setAllowChilds(Integer allowChilds) {
        this.allowChilds = allowChilds;
    }

    public Integer getAllowCats() {
        return allowCats;
    }

    public void setAllowCats(Integer allowCats) {
        this.allowCats = allowCats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPickupaddress() {
        return pickupaddress;
    }

    public void setPickupaddress(String pickupaddress) {
        this.pickupaddress = pickupaddress;
    }

    public String getNoofpassanger() {
        return noofpassanger;
    }

    public void setNoofpassanger(String noofpassanger) {
        this.noofpassanger = noofpassanger;
    }

    public String getDropAddress() {
        return dropAddress;
    }

    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }

    public String getPickupbetween() {
        return pickupbetween;
    }

    public void setPickupbetween(String pickupbetween) {
        this.pickupbetween = pickupbetween;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}