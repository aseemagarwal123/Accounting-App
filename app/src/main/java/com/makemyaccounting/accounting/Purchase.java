package com.makemyaccounting.accounting;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;


public class Purchase extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    String server_url="http://52.66.240.69:8000/api/finance/BuyAndroid/";
    SharedPreferences lt;
    CardView c;
    int count=-1;
    int position;
    int flag=0;
    String id;
    LinearLayout lo;
    ArrayList<EditText> allEds = new ArrayList<EditText>();
    ArrayList<ArrayList<EditText>> obj=new ArrayList<ArrayList<EditText>>();
    final List<String> plantsList = new ArrayList<String>();
    ArrayList <String>temp=new ArrayList<String>();
    int turn;

    JSONArray jarr;
   // int[] arr_id;

    final String MYPREFER = "login";

    Map<String, String> params = new HashMap<>();
    Map<String, String> json = new HashMap<>();
    Map<String, String> json2 = new HashMap<>();
    //ArrayList<ArrayList<EditText>> obj=new ArrayList<ArrayList<EditText>>();
    ArrayList<String> id_array=new ArrayList<String>();
    ArrayList<String> invoice_id=new ArrayList<String>();
    ArrayList<String> qt_array=new ArrayList<String>();
    ArrayList<JSONObject> arr1=new ArrayList<JSONObject>();
    ArrayList<JSONObject> arr=new ArrayList<JSONObject>();
    HashMap<String, String> json3 = new HashMap<String, String>();
    HashMap<String, String> json4= new HashMap<String, String>();

    String t;

    int initial=515;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        lo=(LinearLayout)findViewById(R.id.purchase_layout);
        c=(CardView)findViewById(R.id.c_purchase);
        turn=0;
        try {
            save3();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> aa=
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, plantsList);

        Spinner spin = (Spinner) findViewById(R.id.spinner_item);
        spin.setOnItemSelectedListener(this);
        plantsList.add("Select item");

        // ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,plantsList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner


        spin.setAdapter(aa);


    }



    public void save_pur() throws JSONException {

        final RequestQueue rq = Volley.newRequestQueue(Purchase.this);
        lt = getSharedPreferences(MYPREFER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = lt.edit();
        String t = lt.getString("tok", "");
        t = "Token " + t;

        Toast.makeText(Purchase.this, "" + t, Toast.LENGTH_LONG).show();


        final String finalT1 = t;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Purchase.this, "" + response, Toast.LENGTH_LONG).show();
                rq.stop();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {

                    String responseBody = new String(error.networkResponse.data, "utf-8");
                    // JSONObject data = new JSONObject(responseBody);
                    //JSONArray errors = data.getJSONArray("error");
                    //JSONObject jsonMessage = errors.getJSONObject(0);
                    //String message = jsonMessage.getString("message");
                    String TAG = "MyActivity";
                    Log.i(TAG, "HTTP response cache installation failed:" +responseBody );
                    Toast.makeText(getApplicationContext(), responseBody, Toast.LENGTH_LONG).show();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // Toast.makeText(Purchase.this, ""+error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
                rq.stop();

            }
        })


        {
            @Override
            protected Map<String, String> getParams() {

                // json.put("date", "");
                //json.put("amount", "lucknow");
                //json.put("zip", "282001");

                //json2.put("holder_name","bkkj");
                //json2.put("acc_no","123456");e.printStackTrace();
                //json2.put("ifsc","bkkj");
                //json2.put("branch","bkkj");
                //json2.put("bank_name","bkkj");

              /*  json3.put("discount_rate", "0");
                json3.put("igst", String.valueOf(20.60));
                json3.put("product", String.valueOf(3));
                json3.put("pur_rate", String.valueOf(5000.0));
                json3.put("quantity", String.valueOf(200));
                json3.put("unit", String.valueOf(1));
                json3.put("amount", String.valueOf(700.0));
                json3.put("cgst", String.valueOf(700.0));
                json3.put("sgst", String.valueOf(870.0));
                json3.put("invoice_no", "903124567890");


              /*  for (int i=0;i<obj.size();++i)
                {

                    String abc=obj.get(i).get(0).getText().toString();
                    String def=obj.get(i).get(1).getText().toString();
                    String ghi=obj.get(i).get(2).getText().toString();

                    json3.put("personname",abc);
                    json3.put("email",def);
                    json3.put("number",ghi);
                    arr.add(new JSONObject(json3));
                }
*/

              //  arr.add(new JSONObject(json3));
                params.put("number", "781");
                params.put("android", "y");


                params.put("rem", "1234");
                params.put("initial", "1234");
                //params.put("reminder", "united States");
                params.put("advance_pay", "1235");
                params.put("shipping_pay", "1234");
                params.put("all_discount", "34");
                params.put("pono", "123");
                params.put("shipping_details", "handle with care");


                //params.put("note", "12345");

                //params.put("edited_date", (new JSONObject(json)).toString());
                //params.put("bank_details", (new JSONObject(json2)).toString());

                params.put("products", arr.toString());
                params.put("client", "2");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


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


    public void add2(View view) {
       if(turn==1)
        arr.add(new JSONObject(json4));
        turn=1;

       // Toast.makeText(AddCustomerActivity.this, ""+t, Toast.LENGTH_LONG).show();
        final EditText et=new EditText(Purchase.this);
        EditText e1=new EditText(Purchase.this);
        final Spinner et2=new Spinner(Purchase.this);

        CardView.LayoutParams params = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin=initial;
        params.bottomMargin=15;
        params.leftMargin=50;
        params.rightMargin=50;

        et.setLayoutParams(params);
        c.addView(et);


        initial=initial+200;

        CardView.LayoutParams params1 = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.topMargin=initial;
        params1.bottomMargin=15;
        params1.leftMargin=50;
        params1.rightMargin=50;

        initial=initial+200;

        e1.setLayoutParams(params1);
        c.addView(e1);

        CardView.LayoutParams params2 = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params2.topMargin=initial;
        params2.bottomMargin=15;
        params2.leftMargin=50;
        params2.rightMargin=50;

        initial=initial+200;
        ArrayAdapter<String> aa= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, plantsList);
        et2.setAdapter(aa);
        et2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

               // Toast.makeText(getApplicationContext(), plantsList.get(position), Toast.LENGTH_LONG).show();
                if(position>0)
                {  json4.put("discount_rate", "0");
                    json4.put("igst", String.valueOf(20.60));
                    json4.put("product", String.valueOf(id_array.get(position-1)));
                    json4.put("pur_rate", String.valueOf(50.0));
                    json4.put("quantity", String.valueOf(200));
                    json4.put("unit", String.valueOf(1));
                    json4.put("amount", String.valueOf(700.0));
                    json4.put("cgst", String.valueOf(70.0));
                    json4.put("sgst", String.valueOf(87.0));
                    json4.put("invoice_no", "781");

                  /* arr.add(new JSONObject(json3));
                    Toast.makeText(getApplicationContext(), plantsList.get(position), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), id_array.get(position-1), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), qt_array.get(position-1), Toast.LENGTH_LONG).show();
                    et.setText(qt_array.get(position-1));*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        et2.setLayoutParams(params2);

        c.addView(et2);


        //       Toast.makeText(AddCustomerActivity.this, "" + obj.toString(), Toast.LENGTH_LONG).show();

    }

    public void save3() throws JSONException {

        final RequestQueue rq = Volley.newRequestQueue(Purchase.this);
        lt = getSharedPreferences(MYPREFER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = lt.edit();
        String t = lt.getString("tok", "");
        t = "Token " + t;

        Toast.makeText(Purchase.this, "" + t, Toast.LENGTH_LONG).show();


        final String finalT1 = t;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://52.66.240.69:8080/api/finance/AddProduct/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jarr= new JSONArray(response);
                    int[] arr_id=new int[jarr.length()];

                    for (int i=0;i<jarr.length();i++)
                    {
                        JSONObject jobj = (JSONObject) jarr.get(i);
                        String name=jobj.getString("name");
                        String id=jobj.getString("id");
                        String qty=jobj.getString("quantity");;
                        String invoice =jobj.getString("invoice");
                        id_array.add(id);
                        qt_array.add(qty);
                        plantsList.add(name);
                        invoice_id.add(invoice);

                    }
                   // String pr= String.valueOf(arr_id[0]);
                    Toast.makeText(Purchase.this, "" + invoice_id, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                rq.stop();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {

                    String responseBody = new String(error.networkResponse.data, "utf-8");
                    // JSONObject data = new JSONObject(responseBody);
                    //JSONArray errors = data.getJSONArray("error");
                    //JSONObject jsonMessage = errors.getJSONObject(0);
                    //String message = jsonMessage.getString("message");

                    Toast.makeText(getApplicationContext(), responseBody, Toast.LENGTH_LONG).show();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // Toast.makeText(Purchase.this, ""+error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
                rq.stop();

            }
        })


        {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position>0)
        {
           /* temp.set(0,"0");
            temp.set(0,"0");
            temp.set(0,"0");
            temp.set(0,"0");
            temp.set(0,"0");
            temp.set(0,"0");
            temp.set(0,"0");
            temp.set(0,"0");
             */


            json3.put("discount_rate", "0");
            json3.put("igst", String.valueOf(20.60));
            json3.put("product", String.valueOf(id_array.get(position-1)));
            json3.put("pur_rate", String.valueOf(50.0));
            json3.put("quantity", String.valueOf(200));
            json3.put("unit", String.valueOf(1));
            json3.put("amount", String.valueOf(700.0));
            json3.put("cgst", String.valueOf(70.0));
            json3.put("sgst", String.valueOf(87.0));
            json3.put("invoice_no", "781");



            Toast.makeText(getApplicationContext(), plantsList.get(position), Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), id_array.get(position-1), Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), qt_array.get(position-1), Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void save_purchase(View view) {

        arr.add(new JSONObject(json3));

        try {
            save_pur();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
