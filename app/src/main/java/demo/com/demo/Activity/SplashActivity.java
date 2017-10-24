package demo.com.demo.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import demo.com.demo.SharedPreference.SessionManager;


public class SplashActivity extends AppCompatActivity {

    // Session Manager Class
    SessionManager session;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Session class instance
        session = new SessionManager(getApplicationContext());

        new Handler().postDelayed(new Runnable() {


            public void run() {

                session.checklogin();



            }
        }, SPLASH_TIME_OUT);

    }


}
