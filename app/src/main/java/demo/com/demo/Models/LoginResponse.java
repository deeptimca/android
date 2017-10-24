package demo.com.demo.Models;

import com.google.gson.annotations.SerializedName;


public class LoginResponse
{
    @SerializedName("status")
    private int status;

    @SerializedName("userid")
    private String userid;



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
