package com.streetshopes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import bean.SignupResponce;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.AppConstant;
import utils.Validation;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtRegFirstName, edtRegLastName, edtRegEmail, edtRegPassword;
    private Button btnRegRegister;
    private Unbinder unbinder;
    private TextView edtRegBod;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        unbinder = ButterKnife.bind(RegisterActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtRegFirstName = (EditText) findViewById(R.id.edtRegFirstName);
        edtRegLastName = (EditText) findViewById(R.id.edtRegLastName);
        edtRegEmail = (EditText) findViewById(R.id.edtRegEmail);
        edtRegBod = (TextView) findViewById(R.id.edtRegBod);
        edtRegPassword = (EditText) findViewById(R.id.edtRegPassword);
        btnRegRegister = (Button) findViewById(R.id.btnRegRegister);

        btnRegRegister.setOnClickListener(this);
        edtRegBod.setOnClickListener(this);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unbinder.unbind();
    }


    private boolean chackValidation()
    {
        boolean check = true;
        if (!Validation.hasText(edtRegFirstName)) check = false;
        if (!Validation.hasText(edtRegLastName)) check = false;
        if (!Validation.hasText(edtRegEmail)) check = false;
        if (!Validation.hasText(edtRegPassword)) check = false;

        return check;
    }

    private void processSignup()
    {
        AppConstant.showProgressDialog(RegisterActivity.this);
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("type", AppConstant.TYPE_CREATE_CUSTOMER);
        mapParam.put("firstname", edtRegFirstName.getText().toString().trim());
        mapParam.put("lastname", edtRegLastName.getText().toString().trim());
        mapParam.put("email", edtRegEmail.getText().toString().trim());
        mapParam.put("passwd", edtRegPassword.getText().toString().trim());
        mapParam.put("id_gender", "1");
        mapParam.put("birthday", edtRegBod.getText().toString().trim());
        mapParam.put("active", "1");
        mapParam.put("id_shop", "1");
        mapParam.put("id_default_group", "3");
        mapParam.put("groupBox[]", "3");

        Call<SignupResponce> call = apiService.getSignupInfo(mapParam);
        call.enqueue(new Callback<SignupResponce>() {
            @Override
            public void onResponse(Call<SignupResponce> call, Response<SignupResponce> response)
            {
                SignupResponce signuUpResponce = response.body();
                AppConstant.dismissProgressDialog();
                if(signuUpResponce.getStatus() == 1)
                {
                    loginActivity();
                }
                else
                {
                    AppConstant.showAlertDialog(RegisterActivity.this, "Failed to Register.Email Address Already exist.");
                }
            }

            @Override
            public void onFailure(Call<SignupResponce> call, Throwable t)
            {
                // Log error here since request failed
                Log.e("SignUpActivity", t.toString());
                AppConstant.showAlertDialog(RegisterActivity.this, getResources().getString(R.string.netrwork_error));
                AppConstant.dismissProgressDialog();
            }
        });
    }

    private void loginActivity()
    {
        Intent in = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(in);
        finish();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.edtRegBod:
                AppConstant.setDate(RegisterActivity.this, edtRegBod);
                break;
            case R.id.btnRegRegister:
                if(chackValidation())
                {
                    processSignup();
                }
                break;

        }
    }
}
