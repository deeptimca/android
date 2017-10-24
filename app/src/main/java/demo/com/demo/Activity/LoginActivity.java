package demo.com.demo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import demo.com.demo.Models.LoginResponse;
import demo.com.demo.RetrofitClient.HomApiClient;
import demo.com.demo.RetrofitClient.LoginApiInterface;
import demo.com.demo.SharedPreference.SessionManager;
import demo.com.demo.utils.Validation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity  {

    SessionManager session;
    private EditText et_login, et_password;
    private Button but_submit;
  
    private String userName, userPassword;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        
        setContentView(R.layout.login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initViews();
        but_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                userName = et_login.getText().toString();
                userPassword = et_password.getText().toString();

                if (checkValidation()) {

                    session.createuseridsession("123",false);

                    Intent  i = new Intent(LoginActivity.this, HomeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(i);

                    //use  moke api request for login
                    // networkCall(userName,userPassword);
                }

            }
        });
        
      
    }
   
    

    private void initViews() {
        et_login = (EditText) findViewById(R.id.et_email);
     
        et_password = (EditText) findViewById(R.id.et_password);
       
        but_submit = (Button) findViewById(R.id.but_login);
    }

    private void networkCall(final String userName, final String userPassword) {
        LoginApiInterface service = HomApiClient.createService(LoginApiInterface.class);
        Call<LoginResponse> loginResponse = service.getLoginResponse(userName, userPassword);
        loginResponse.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.body() != null ) {
                    switch (response.body().getStatus()) {

                        case 1:
                            session.createuseridsession(response.body().getUserid(),false);

                              Intent  i = new Intent(LoginActivity.this, HomeActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(i);
                            break;

                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Something went wrong,Retry Again", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                 Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }



   

    // Validation Check
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.validation(et_login))
        {
            et_login.requestFocus();
            ret = false;
        }

        else   if (!Validation.validation(et_password))
        {
            et_password.requestFocus();
            ret = false;
        }

        return ret;
    }


}