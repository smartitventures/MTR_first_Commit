package mtr.Response.deviceidValidation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseValidateID {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("payload")
    @Expose
    private Payload payload;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

}