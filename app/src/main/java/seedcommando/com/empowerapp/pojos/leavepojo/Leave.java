package seedcommando.com.empowerapp.pojos.leavepojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by commando4 on 3/16/2018.
 */

public class Leave {
 @SerializedName("status")
   private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
