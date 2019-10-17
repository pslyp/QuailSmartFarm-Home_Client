package com.pslyp.quailsmartfarm_home_client.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.pslyp.quailsmartfarm_home_client.R;
import com.pslyp.quailsmartfarm_home_client.models.Response;
import com.pslyp.quailsmartfarm_home_client.services.Prefs;
import com.pslyp.quailsmartfarm_home_client.services.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView createNewUserText;
    private TextInputLayout emailText, passwordText;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initInstance();
    }

    private void initInstance() {
        emailText = findViewById(R.id.email_text_input);
        passwordText = findViewById(R.id.password_text_input);
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);
        createNewUserText = findViewById(R.id.create_user_text_view);
        createNewUserText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button :
                signIn();
                break;
            case R.id.create_user_text_view :
                startActivity(new Intent(SigninActivity.this, SignUpActivity.class));
                finish();
                break;
        }
    }

    private void signIn() {
        String email = emailText.getEditText().getText().toString().trim();
        String password = passwordText.getEditText().getText().toString().trim();

        Call<Response> call = RetrofitClient.getInstance().api().signIn(email, password);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response res = response.body();

                if(res.getStatus() == 200) {
                    if(res.getMessage().equals("success")) {
                        Prefs prefs = new Prefs.Builder(getApplicationContext())
                                .name("Authen")
                                .mode(MODE_PRIVATE)
                                .build();

                        prefs.putBoolean("SIGNIN", true);

                        startActivity(new Intent(SigninActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(SigninActivity.this, "EiEi", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e("Sign in", t.getMessage());
            }
        });
    }
}
