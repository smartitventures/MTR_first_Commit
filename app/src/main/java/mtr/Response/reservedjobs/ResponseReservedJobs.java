
package mtr.Response.reservedjobs;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseReservedJobs {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("payload")
    @Expose
    private List<Payload> payload = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Payload> getPayload() {
        return payload;
    }

    public void setPayload(List<Payload> payload) {
        this.payload = payload;
    }

}