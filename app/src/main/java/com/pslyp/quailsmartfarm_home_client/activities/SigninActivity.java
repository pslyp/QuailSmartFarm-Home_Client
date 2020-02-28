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
import com.pslyp.quailsmartfarm_home_client.models.SignInResponse;
import com.pslyp.quailsmartfarm_home_client.services.Prefs;
import com.pslyp.quailsmartfarm_home_client.services.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView createNewUserText;
    private TextInputLayout emailText, passwordText;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

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
                startActivity(new Intent(SigninActivity.this, SignupActivity.class));
                break;
        }
    }

    private void signIn() {
        final String email = emailText.getEditText().getText().toString().trim();
        final String password = passwordText.getEditText().getText().toString().trim();
        
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter an email & password!", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<SignInResponse> call = RetrofitClient.getInstance().api().signIn(email, password);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                SignInResponse res = response.body();
                int status = res.getStatus();

                if(status == 200) {
                    Prefs prefsAuthen = new Prefs.Builder(getApplicationContext())
                            .name("Authen")
                            .mode(MODE_PRIVATE)
                            .build();

                    prefsAuthen.putBoolean("SIGNIN", true);
                    prefsAuthen.putString("TOKEN", res.getToken());

                    Prefs prefsProfile = new Prefs.Builder(getApplicationContext())
                            .name("Profile")
                            .mode(MODE_PRIVATE)
                            .build();

                    prefsProfile.putString("ID", res.getId());
                    prefsProfile.putString("USERNAME", res.getUsername());
                    prefsProfile.putString("EMAIL", email);
//                    prefsProfile.putString("PICTURE", user.getPicture());

                    startActivity(new Intent(SigninActivity.this, MainActivity.class));
                    finish();
                } else if(status == 401 || status == 416) {
                    Toast.makeText(SigninActivity.this, "Wrong email or password!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.e("Sign in", t.getMessage());
            }
        });
    }
}
