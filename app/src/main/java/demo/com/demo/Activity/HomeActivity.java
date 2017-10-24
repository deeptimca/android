package demo.com.demo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import demo.com.demo.SharedPreference.SessionManager;
/**
 * Created by deeptis.sw on 24-Oct-17.
 */

public class HomeActivity extends AppCompatActivity {
    SessionManager session;
    String userid;
    private TextView txt1;
    private  Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initViews();
        // Session class instance
        session = new SessionManager(getApplicationContext());

        userid = session.getuseridfromsession();

        if(userid !=null)
        {
            txt1.setText("They are logged in");

        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
            }
        });

    }
    private void initViews() {
        txt1 = (TextView) findViewById(R.id.txt1);

        logout = (Button) findViewById(R.id.btnlogout);
    }

}
