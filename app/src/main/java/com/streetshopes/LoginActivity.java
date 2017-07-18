package com.streetshopes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import bean.SignupResponce;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pojo.LoginResponce;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.AppConstant;
import utils.UtilitySharedPreferences;
import utils.Validation;

import static utils.AppConstant.cart_id;
import static utils.AppConstant.user_id;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edtUserName)
    EditText edtUserName;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.txtForgotPassword)
    TextView txtForgotPassword;
    @BindView(R.id.txtCreateAccount)
    TextView txtCreateAccount;
    @BindView(R.id.imgFaceBook)
    ImageView imgFaceBook;
    @BindView(R.id.imgTwitter)
    ImageView imgTwitter;
    @BindView(R.id.imgGooglePlus)
    ImageView imgGooglePlus;

    private Unbinder unbinder;
    public   SharedPreferences pref;
    public    Resources res;
    public   SharedPreferences.Editor ed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        getSupportActionBar().hide();

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        ed = pref.edit();

        //Autofill saved username
        if (!pref.contains("UserName"))
        {
            ed.putString("UserName", "");
            ed.commit();
        }
        else
        {
            edtUserName.setText(pref.getString("UserName",null));
        }

        //Autofill saved password
        if (!pref.contains("Password"))
        {
            ed.putString("Password", "");
            ed.commit();
        }
        else
        {
            edtPassword.setText(pref.getString("Password",null));
        }

        if (!pref.contains("cart_id"))
        {
            ed.putString("cart_id", "");
            ed.commit();
        }
        else
        {
            cart_id = pref.getString("cart_id",null);

            System.out.println("Cool "+pref.getString("cart_id",null));
        }

    }

    @OnClick({R.id.btnSignIn, R.id.txtForgotPassword, R.id.txtCreateAccount, R.id.imgFaceBook, R.id.imgTwitter, R.id.imgGooglePlus})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnSignIn:

                if (chackValidation()){
                    processLogin();
                }

                break;
            case R.id.txtForgotPassword:
                break;
            case R.id.txtCreateAccount:
                intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.imgFaceBook:
                break;
            case R.id.imgTwitter:
                break;
            case R.id.imgGooglePlus:
                break;
        }
    }



    private boolean chackValidation(){

        boolean check = true;
        if (!Validation.hasText(edtUserName)) check = false;
        if (!Validation.hasText(edtPassword)) check = false;

        return check;
    }

    private void processLogin() {
        AppConstant.showProgressDialog(LoginActivity.this);
        final ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("type", AppConstant.TYPE_LOGIN_CUSTOMER);
        mapParam.put("email", edtUserName.getText().toString().trim());
        mapParam.put("passwd", edtPassword.getText().toString().trim());

        Call<LoginResponce> call = apiService.getLoginInfo(mapParam);
        call.enqueue(new Callback<LoginResponce>() {
            @Override
            public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {
                LoginResponce loginResponce = response.body();
                AppConstant.dismissProgressDialog();
                if(loginResponce.getStatus() == 1)
                {
                    UtilitySharedPreferences.putStringValueInSharedPreference(LoginActivity.this, AppConstant.PRF_SIGNUP_USERID, loginResponce.getData().getId());

                    //save username and password
                    ed.putString("UserName", edtUserName.getText().toString().trim());
                    ed.commit();

                    ed.putString("Password", edtPassword.getText().toString().trim());
                    ed.commit();

                    System.out.println("Costing "+loginResponce.getData().getId());
                    user_id =  loginResponce.getData().getId();

                    moveToNext();

                } else {
                    AppConstant.showAlertDialog(LoginActivity.this, "Email or Password incorrect");
                }
            }

            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {
                // Log error here since request failed
                Log.e("SignUpActivity", t.toString());
                AppConstant.showAlertDialog(LoginActivity.this, getResources().getString(R.string.netrwork_error));
                AppConstant.dismissProgressDialog();
            }
        });
    }

    private void moveToNext() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
