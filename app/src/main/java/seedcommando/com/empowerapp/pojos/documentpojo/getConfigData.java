package seedcommando.com.empowerapp.pojos.documentpojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class getConfigData {
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;
    private Data data;



    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
