package com.makemyaccounting.accounting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    EditText bn,dis,gstno,address,phno,country,state;
    SharedPreferences sp;
    final String MYPREFER = "signup";
    String server_url="http://52.66.240.69:8000/api/finance/Register/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        }

    public void toreg2(View view) {

        sp = getSharedPreferences( MYPREFER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        final String user = sp.getString("user","");
        final String email = sp.getString("email","");
        final String password1 = sp.getString("password1","");
        final String password2 = sp.getString("password2","");


        final RequestQueue rq= Volley.newRequestQueue(RegistrationActivity.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(RegistrationActivity.this,""+response,Toast.LENGTH_LONG).show();
                rq.stop();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             /*   try {

                    String responseBody = new String(error.networkResponse.data, "utf-8");
                    // JSONObject data = new JSONObject(responseBody);
                    //JSONArray errors = data.getJSONArray("error");
                    //JSONObject jsonMessage = errors.getJSONObject(0);
                    //String message = jsonMessage.getString("message");
                    Toast.makeText(getApplicationContext(), responseBody, Toast.LENGTH_LONG).show();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }*/
                //Toast.makeText(RegistrationActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
                error.printStackTrace();

                rq.stop();

            }
        })

        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("password1",password1);
                params.put("password2",password2);
                params.put("username",user);
                params.put("email",email);
                params.put("buisness_name","xyz");
                params.put("dir_name","svvvrv");
                params.put("gstnumber","gstin123456");
                params.put("regis_mood","F");
                params.put("address","cbcbch");
                params.put("bank_details","nhchdchc");
                params.put("shop_right","R");
                params.put("state","cewgvcyewvcg");
                params.put("contact","0986433");
                params.put("country","vecvv");
                params.put("details","efwwvwrvrv");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
      
        rq.add(stringRequest);


    }
}
