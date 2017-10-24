package demo.com.demo.RetrofitClient;


import demo.com.demo.Models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface LoginApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> getLoginResponse(
            @Field("username") String username,
            @Field("userpassword") String userpassword);

}
