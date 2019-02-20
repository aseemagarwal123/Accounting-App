package com.makemyaccounting.accounting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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


public class Add_item extends AppCompatActivity {
    String server_url="http://52.66.240.69:8000/api/finance/AddProduct/";
    String type;
    SharedPreferences lt;
    TextView tv;
    final String MYPREFER = "login";
    CardView card;
    Map<String, String> params = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        EditText simpleEditText2 = (EditText) findViewById(R.id.qty);
        EditText simpleEditText5 = (EditText) findViewById(R.id.hsn);

        EditText simpleEditText4 = (EditText) findViewById(R.id.tax);

        EditText simpleEditText3 = (EditText) findViewById(R.id.unit);
       tv=(TextView)findViewById(R.id.t);
        card=(CardView)findViewById(R.id.cv1);
        lt = getSharedPreferences( MYPREFER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=lt.edit();
        String cat = lt.getString("type","");
        if (cat.equals("S"))
        {
         tv.setText("Services");
          simpleEditText2.setVisibility(View.INVISIBLE);
            CardView.LayoutParams params2 = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params2.topMargin=350;
            params2.leftMargin=50;
            params2.rightMargin=50;
            simpleEditText3.setLayoutParams(params2);
            CardView.LayoutParams params3 = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params3.topMargin=500;
            params3.leftMargin=50;
            params3.rightMargin=50;
            simpleEditText4.setLayoutParams(params3);
            CardView.LayoutParams params4 = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params4.topMargin=650;
            params4.leftMargin=50;
            params4.rightMargin=50;
            simpleEditText5.setLayoutParams(params4);
        }

    }






    public void save(View view) throws JSONException {


        EditText simpleEditText = (EditText) findViewById(R.id.product);
        final String name = simpleEditText.getText().toString();

        EditText simpleEditText1 = (EditText) findViewById(R.id.desc);
        final String description = simpleEditText1.getText().toString();
        EditText simpleEditText2 = (EditText) findViewById(R.id.qty);
        String quantity = simpleEditText2.getText().toString();



        EditText simpleEditText3 = (EditText) findViewById(R.id.unit);
        final String unit = simpleEditText3.getText().toString();

        EditText simpleEditText4 = (EditText) findViewById(R.id.tax);
        final String tax = simpleEditText4.getText().toString();

        EditText simpleEditText5 = (EditText) findViewById(R.id.hsn);
        final String hsn = simpleEditText5.getText().toString();

        EditText simpleEditText6 = (EditText) findViewById(R.id.purp);
        final String purchasep = simpleEditText6.getText().toString();

        EditText simpleEditText7 = (EditText) findViewById(R.id.slp);
        final String salep = simpleEditText7.getText().toString();

        EditText simpleEditText8 = (EditText) findViewById(R.id.cess);
        final String cess = simpleEditText8.getText().toString();


        lt = getSharedPreferences( MYPREFER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=lt.edit();
        String category = lt.getString("type","");
        if(category.equals("P")) {
            type = "P";
        }
        else{
            type="S";

       quantity="0";


        }

        String t = lt.getString("tok","");
        t="Token "+t;
        Toast.makeText(Add_item.this, "" + t, Toast.LENGTH_LONG).show();


        final RequestQueue rq = Volley.newRequestQueue(Add_item.this);


        final String finalT1 = t;

        final String finalQuantity = quantity;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Add_item.this, "" + response, Toast.LENGTH_LONG).show();
                rq.stop();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Add_item.this, ""+error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
                rq.stop();

            }
        })

        {
            @Override
            protected Map<String, String> getParams() {

                params.put("type_product", type);
                params.put("name",name);
                params.put("desc", description);
                params.put("unit", unit);
                params.put("quantity", finalQuantity);
                params.put("pur_rate", purchasep);
                params.put("sell_price",salep);
                params.put("sac_hsn", hsn);
                params.put("cess_rate", cess);
                params.put("tax_rate", tax);

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
    @Override
    public void onBackPressed() {

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
