package com.makemyaccounting.accounting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
public class AddCustomerActivity extends AppCompatActivity {
    String server_url="http://52.66.240.69:8000/api/finance/AddClient/";
    CardView c;
    LinearLayout lo;
    EditText cpers,cpho,cemail;
    SharedPreferences lt;

    final String MYPREFER = "login";

    Map<String, String> params = new HashMap<>();
    Map<String, String> json = new HashMap<>();
    Map<String, String> json2 = new HashMap<>();
    ArrayList<ArrayList<EditText>> obj=new ArrayList<ArrayList<EditText>>();

    ArrayList<JSONObject> arr=new ArrayList<JSONObject>();
    HashMap<String, String> json3 = new HashMap<String, String>();
    String t;

    int initial=515;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);


        cpers=(EditText)findViewById(R.id.cpr);
        cpho=(EditText)findViewById(R.id.cph);
        cemail=(EditText)findViewById(R.id.pemail);

        lt = getSharedPreferences( MYPREFER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=lt.edit();
        String t = lt.getString("tok","");
        t="Token "+t;

        Toast.makeText(AddCustomerActivity.this, "" + t, Toast.LENGTH_LONG).show();

    }




    public void save(View view) throws JSONException {
        final String contact=cpers.getText().toString();
        final String contactph=cpho.getText().toString();
        final String contactemail=cemail.getText().toString();
        final RequestQueue rq = Volley.newRequestQueue(AddCustomerActivity.this);
        lt = getSharedPreferences( MYPREFER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=lt.edit();
        String t = lt.getString("tok","");
        t="Token "+t;

        Toast.makeText(AddCustomerActivity.this, "" + t, Toast.LENGTH_LONG).show();


        final String finalT1 = t;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(AddCustomerActivity.this, "" + response, Toast.LENGTH_LONG).show();
                rq.stop();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddCustomerActivity.this, ""+error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
                rq.stop();

            }
        })

        {
            @Override
            protected Map<String, String> getParams() {

                json.put("address1", "kutlupur namnair");
                json.put("city", "lucknow");
                json.put("zip", "282001");

                json2.put("holder_name","bkkj");
                json2.put("acc_no","123456");
                json2.put("ifsc","bkkj");
                json2.put("branch","bkkj");
                json2.put("bank_name","bkkj");

                json3.put("personname",contact);
                json3.put("email",contactemail);
                json3.put("number",contactph);
                arr.add(new JSONObject(json3));
                for (int i=0;i<obj.size();++i)
                {

                    String abc=obj.get(i).get(0).getText().toString();
                    String def=obj.get(i).get(1).getText().toString();
                    String ghi=obj.get(i).get(2).getText().toString();

                    json3.put("personname",abc);
                    json3.put("email",def);
                    json3.put("number",ghi);
                    arr.add(new JSONObject(json3));
                }


                params.put("head","1");
                params.put("state", "andhra pradesh");
                params.put("gst_number", "12345");
                params.put("email", "srivddfsa@gamil.com");
                params.put("country", "united States");
                params.put("buisness_name", "canada");
                params.put("details", "ffchvhvg");
                params.put("shipping_address", "ffchvhvg");
                params.put("contact", "ffchvhvg");
                params.put("address", (new JSONObject(json)).toString());
                params.put("bank_details", (new JSONObject(json2)).toString());
                params.put("contact_person",arr.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();




                params.put("Content-Type", "application/json");
                params.put("cache-control", "no-cache");
                params.put("Authorization", finalT1);
                return params;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        rq.add(stringRequest);
    }

    public void add(View view) {
        ArrayList<EditText> allEds = new ArrayList<EditText>();
        lt = getSharedPreferences( MYPREFER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=lt.edit();
        String t = lt.getString("tok","");
        t="Token "+t;

        Toast.makeText(AddCustomerActivity.this, ""+t, Toast.LENGTH_LONG).show();
        EditText et=new EditText(AddCustomerActivity.this);
        EditText e1=new EditText(AddCustomerActivity.this);
        EditText et2=new EditText(AddCustomerActivity.this);

        lo=(LinearLayout)findViewById(R.id.ll);
        c=(CardView)findViewById(R.id.cv);
        CardView.LayoutParams params = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin=initial;
        params.bottomMargin=15;
        params.leftMargin=50;
        params.rightMargin=50;

        et.setLayoutParams(params);
        c.addView(et);
        allEds.add(et);

        initial=initial+200;

        CardView.LayoutParams params1 = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.topMargin=initial;
        params1.bottomMargin=15;
        params1.leftMargin=50;
        params1.rightMargin=50;

        initial=initial+200;

        e1.setLayoutParams(params1);
        c.addView(e1);
        allEds.add(e1);
        CardView.LayoutParams params2 = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.topMargin=initial;
        params2.bottomMargin=15;
        params2.leftMargin=50;
        params2.rightMargin=50;

        initial=initial+200;

        et2.setLayoutParams(params2);
        c.addView(et2);
        allEds.add(et2);
        obj.add(allEds);
 //       Toast.makeText(AddCustomerActivity.this, "" + obj.toString(), Toast.LENGTH_LONG).show();

    }
    @Override
    public void onBackPressed() {


        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

}






