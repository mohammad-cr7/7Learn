package com.example.sshahini.myapplication.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sshahini.myapplication.ApiService;
import com.example.sshahini.myapplication.MyApplication;
import com.example.sshahini.myapplication.R;
import com.example.sshahini.myapplication.UserSharedPrefManager;

public class LoginActivity extends AppCompatActivity {

    private Button changeAuthenticationState;
    private boolean isSigningUp=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupViews();
    }

    private void setupViews() {
        Typeface typeface=MyApplication.getIranianSansFont(this);
        final EditText emailEditText=(EditText)findViewById(R.id.edit_text_email);
        emailEditText.setTypeface(typeface);

        final EditText passwordEditText=(EditText)findViewById(R.id.edit_text_password);
        passwordEditText.setTypeface(typeface);

        final TextView authenticationTextView=(TextView)findViewById(R.id.label_authentication);
        final FloatingActionButton authenticateButton=(FloatingActionButton)findViewById(R.id.float_button_authenticate);
        changeAuthenticationState=(Button)findViewById(R.id.button_authentication);
        changeAuthenticationState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigningUp=!isSigningUp;
                if (isSigningUp){
                    authenticationTextView.setText(getString(R.string.label_sign_up));
                    changeAuthenticationState.setText(getString(R.string.label_login));
                    authenticateButton.setImageResource(R.drawable.ic_action_sign_up);
                }else {
                    authenticationTextView.setText(getString(R.string.label_login));
                    changeAuthenticationState.setText(getString(R.string.label_sign_up));
                    authenticateButton.setImageResource(R.drawable.ic_action_login);
                }
            }
        });

        authenticateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=emailEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                final UserSharedPrefManager sharedPrefManager=new UserSharedPrefManager(LoginActivity.this);
                if (isSigningUp){
                    if (!email.isEmpty() && !password.isEmpty()){
                        if (password.length()>=4){
                            if (isEmailValid(email)){
                                ApiService apiService=new ApiService(LoginActivity.this);
                                apiService.signUpUser(email, password, new ApiService.OnSignupComplete() {
                                    @Override
                                    public void onSignUp(int responseStatus) {
                                        switch (responseStatus){
                                            case ApiService.STATUS_EMAIL_EXIST:
                                                Toast.makeText(LoginActivity.this,"کاربری با این ایمیل موجود است.",Toast.LENGTH_SHORT).show();
                                                break;
                                            case ApiService.STATUS_FAILED:
                                                Toast.makeText(LoginActivity.this,"متاسفانه ثبت نام انجام نشد.",Toast.LENGTH_SHORT).show();
                                                break;
                                            case ApiService.STATUS_SUCCESS:
                                                Toast.makeText(LoginActivity.this,"ثبت نام با موفقیت انجام شد",Toast.LENGTH_SHORT).show();

                                                sharedPrefManager.saveUserLoginInfo(email);
                                                Intent resultIntent=new Intent();
                                                resultIntent.putExtra("email",email);
                                                setResult(MainActivity.RESULT_OK,resultIntent);
                                                finish();
                                                break;
                                        }
                                    }
                                });
                            }else {
                                Toast.makeText(LoginActivity.this,"ایمیل معتبر نیست.",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(LoginActivity.this,"طول پسورد باید حداقل 4 کاراکتر باشد.",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this,"ایمیل و پسورد نمی توانند خالی باشند.",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (!email.isEmpty() && !password.isEmpty()){
                        ApiService apiService=new ApiService(LoginActivity.this);
                        apiService.loginUser(email, password, new ApiService.OnLoginResponse() {
                            @Override
                            public void onResponse(boolean success) {
                                if (success){
                                    Toast.makeText(LoginActivity.this,"خوش آمدید",Toast.LENGTH_SHORT).show();
                                    sharedPrefManager.saveUserLoginInfo(email);
                                    Intent resultIntent=new Intent();
                                    resultIntent.putExtra("email",email);
                                    setResult(MainActivity.RESULT_OK,resultIntent);
                                    finish();
                                }else {
                                    Toast.makeText(LoginActivity.this,"اطلاعات صحیح نیست",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(LoginActivity.this,"ایمیل و پسورد نمی توانند خالی باشند.",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private boolean isEmailValid(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
