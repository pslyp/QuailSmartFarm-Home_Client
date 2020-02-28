package com.pslyp.quailsmartfarm_home_client.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.pslyp.quailsmartfarm_home_client.R;
import com.pslyp.quailsmartfarm_home_client.models.BaseResponse;
import com.pslyp.quailsmartfarm_home_client.models.User;
import com.pslyp.quailsmartfarm_home_client.services.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;

public class SignupActivity extends AppCompatActivity {

    private TextInputLayout mUsernameText, mEmailText, mPasswordText;
    private Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initInstance();
    }

    private void initInstance() {
        mUsernameText = findViewById(R.id.username_text_input);
        mEmailText = findViewById(R.id.email_text_input);
        mPasswordText = findViewById(R.id.password_text_input);
        mSignupButton = findViewById(R.id.sign_up_button);
        mSignupButton.setOnClickListener(signup);
    }

    private View.OnClickListener signup = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String id = String.valueOf((int) (Math.random() * 100000));
            final String username = mUsernameText.getEditText().getText().toString().trim();
            final String email = mEmailText.getEditText().getText().toString().trim();
            final String password = mPasswordText.getEditText().getText().toString().trim();

            User user = new User(id, username, email, password);

            Call<BaseResponse> call = RetrofitClient.getInstance().api().signUp(user);
            call.enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, retrofit2.Response<BaseResponse> response) {
                    int status = response.body().getStatus();

                    if(status == 201) {
                        Toast.makeText(SignupActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    Log.e("Sign up", t.getMessage());
                }
            });
        }
    };
}
