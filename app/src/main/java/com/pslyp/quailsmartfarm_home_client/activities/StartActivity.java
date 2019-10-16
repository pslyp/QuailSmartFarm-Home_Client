package com.pslyp.quailsmartfarm_home_client.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.pslyp.quailsmartfarm_home_client.R;
import com.pslyp.quailsmartfarm_home_client.services.Prefs;

public class StartActivity extends AppCompatActivity {

    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        authenUser();
        initInstance();
    }

    private void authenUser() {
        prefs = new Prefs.Builder(this)
                .name("Authen")
                .mode(MODE_PRIVATE)
                .build();

        if(prefs.getBoolean("LOGIN", false)) {
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(StartActivity.this, SigninActivity.class));
            finish();
        }
    }

    private void initInstance() {

    }
}
