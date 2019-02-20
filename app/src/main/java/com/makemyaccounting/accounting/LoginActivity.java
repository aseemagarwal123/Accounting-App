package com.makemyaccounting.accounting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.makemyaccounting.dashboard.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText username, password;
    SharedPreferences lt;
    final String MYPREFER = "login";




    String server_url = "http://52.66.240.69:8000/api/finance/Login/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);
        username = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText3);


    }


    public void signup(View view) {
        //CreateAlertDialogWithRadioButtonGroup();
        Intent i=new Intent(getApplicationContext(),SignupActivity.class);
        startActivity(i);
    }

    public void login(View view) {

        final String user = username.getText().toString();
        final String pass = password.getText().toString();
       // Toast.makeText(LoginActivity.this, user, Toast.LENGTH_LONG).show();

        final RequestQueue rq = Volley.newRequestQueue(LoginActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String token = jsonObject.getString("token");
                    Toast.makeText(LoginActivity.this, "" + token, Toast.LENGTH_LONG).show();
                    lt = getSharedPreferences(MYPREFER, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = lt.edit();
                    editor.putString("tok", token);
                    editor.commit();
                    rq.stop();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } catch (Exception ex) {
                    ex.printStackTrace();


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



                Toast.makeText(LoginActivity.this, ""+error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();

                rq.stop();

            }
        })

        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", user);
                params.put("password", pass);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("cache-control", "no-cache");
                params.put("Accept","application/json, text/plain, */*");
                //params.put("Authorization", "Token 2fa8d8ca8788f1302229271430b66255b616f412");
                return params;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        rq.add(stringRequest);
    }





}





