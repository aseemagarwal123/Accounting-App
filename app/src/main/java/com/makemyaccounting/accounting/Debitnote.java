package com.makemyaccounting.accounting;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Debitnote extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    //String server_url="http://52.66.240.69:8000/api/finance/BuyAndroid/";
    SharedPreferences lt;
    CardView c;
    int count=-1;
    int position;
    int flag=0;
    int p;
    LinearLayout lo;
    ArrayList<EditText> allEds = new ArrayList<EditText>();
    ArrayList<ArrayList<EditText>> obj=new ArrayList<ArrayList<EditText>>();
    final List<String> plantsList = new ArrayList<String>();
    final List<String> idlist = new ArrayList<String>();
    final List<String> prolist = new ArrayList<String>();
    final List<String> pricelist = new ArrayList<String>();
    final List<String> salelist = new ArrayList<String>();
    ArrayList <String>temp=new ArrayList<String>();
    HashMap<String, String> json3 = new HashMap<String, String>();
    HashMap<String, String> json4= new HashMap<String, String>();

    int turn;

    JSONArray jarr;
    // int[] arr_id;

    final String MYPREFER = "login";

    Map<String, String> params = new HashMap<>();
    Map<String, String> json = new HashMap<>();
    Map<String, String> json2 = new HashMap<>();
    //ArrayList<ArrayList<EditText>> obj=new ArrayList<ArrayList<EditText>>();
    ArrayList<String> id_array=new ArrayList<String>();
    ArrayList<String> qt_array=new ArrayList<String>();
    ArrayList<JSONObject> arr1=new ArrayList<JSONObject>();
    ArrayList<JSONObject> arr=new ArrayList<JSONObject>();


    String t;

    int initial=515;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debitnote);
        lo=(LinearLayout)findViewById(R.id.layout_credit);
        c=(CardView)findViewById(R.id.c_credit);
        turn=0;
        spinner();
        try {
            save3();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> ab=
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, plantsList);

        Spinner spin = (Spinner) findViewById(R.id.Invoice_list);

        spin.setOnItemSelectedListener(this);


        plantsList.add("invoice no");

        // ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,plantsList);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner


        spin.setAdapter(ab);

    }



    public void save3() throws JSONException {

        final RequestQueue rq = Volley.newRequestQueue(Debitnote.this);
        lt = getSharedPreferences(MYPREFER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = lt.edit();
        String t = lt.getString("tok", "");
        t = "Token " + t;

        Toast.makeText(Debitnote.this, "" + t, Toast.LENGTH_LONG).show();


        final String finalT1 = t;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://52.66.240.69:8080/api/finance/invoice/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    JSONArray jarr =jobj.getJSONArray("buy");
                    for ( int i =0 ;i<jarr.length();++i)
                    {

                        JSONObject jtemp = (JSONObject) jarr.get(i);
                        String no= jtemp.getString("number");
                        String id=jtemp.getString("id");
                        plantsList.add(no);
                        idlist.add(id);

                    }
                    // String pr= String.valueOf(arr_id[0]);
                    //Toast.makeText(Creditnote.this, "" + idlist, Toast.LENGTH_LONG).show();
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
        Toast.makeText(getApplicationContext(), plantsList.get(position), Toast.LENGTH_LONG).show();
        if (position>0)
        { p=position-1;
            try {
                save_credit();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void spinner()
    {
        //Arra
        ArrayAdapter<String> ac=
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, prolist);
        //Spinner spin = (Spinner) findViewById(R.id.Invoice_list);
        Spinner spin2 = (Spinner) findViewById(R.id.spinner_item);
        //spin.setOnItemSelectedListener(this);


        // plantsList.add("invoice no");
        prolist.add("select item");

        // ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,plantsList);
        //ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        ac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(ac);

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                // Toast.makeText(getApplicationContext(), plantsList.get(position), Toast.LENGTH_LONG).show();
                if(position>0)
                { json3.put("discount_rate", "0");
                    json3.put("discount_amount","67");
                    json3.put("igst", String.valueOf(20.60));
                    //json3.put("product", String.valueOf(id_array.get(position)));
                    json3.put("pur_rate", prolist.get(position-1));
                    json3.put("quantityreturn", String.valueOf(200));
                    json3.put("unit", String.valueOf(1));
                    json3.put("amount", String.valueOf(700.0));
                    json3.put("purchase",salelist.get(position-1));
                    json3.put("name",prolist.get(position));
                    json3.put("price",pricelist.get(position-1));
                    json3.put("cgst", String.valueOf(70.0));
                    json3.put("sgst", String.valueOf(87.0));
                    Toast.makeText(getApplicationContext(), prolist.get(position), Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        //spin.setAdapter(ab);




    }

    public void save_credit() throws JSONException {

        final RequestQueue rq = Volley.newRequestQueue(Debitnote.this);
        lt = getSharedPreferences(MYPREFER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = lt.edit();
        String t = lt.getString("tok", "");
        t = "Token " + t;

        final String finalT1 = t;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://52.66.240.69:8080/api/finance/InvoiceDetail/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject pro=new JSONObject(response);
                    prolist.clear();
                    salelist.clear();
                    pricelist.clear();
                    prolist.add("select item");
                    JSONArray prarr=pro.getJSONArray("products");

                    for (int i=0;i<prarr.length();i++)
                    {
                        prolist.add(prarr.getJSONObject(i).getString("product_name"));
                        salelist.add(prarr.getJSONObject(i).getString("id"));
                        pricelist.add(prarr.getJSONObject(i).getString("pur_rate"));

                    }
                    Toast.makeText(Debitnote.this, "" + salelist, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                // Toast.makeText(Creditnote.this, "" + response, Toast.LENGTH_LONG).show();
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

                params.put("invoice", idlist.get(p));
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



    public void add2_debitnote(View view) {
        if(turn==1)
            arr.add(new JSONObject(json4));
        turn=1;

        // Toast.makeText(AddCustomerActivity.this, ""+t, Toast.LENGTH_LONG).show();
        final EditText et=new EditText(Debitnote.this);
        EditText e1=new EditText(Debitnote.this);
        final Spinner et2=new Spinner(Debitnote.this);

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
        ArrayAdapter<String> aa= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, prolist);
        et2.setAdapter(aa);
        et2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                // Toast.makeText(getApplicationContext(), plantsList.get(position), Toast.LENGTH_LONG).show();
                if(position>0)
                {   json4.put("discount_rate", "0");
                    json4.put("discount_amount","67");
                    json4.put("igst", String.valueOf(20.60));
                    //json3.put("product", String.valueOf(id_array.get(position)));
                    json4.put("pur_rate", prolist.get(position-1));
                    json4.put("quantityreturn", String.valueOf(200));
                    json4.put("unit", String.valueOf(1));
                    json4.put("amount", String.valueOf(700.0));
                    json4.put("purchase",salelist.get(position-1));
                    json4.put("name",prolist.get(position));
                    json4.put("price",pricelist.get(position-1));
                    json4.put("cgst", String.valueOf(70.0));
                    json4.put("sgst", String.valueOf(87.0));

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



    public void final_save_credit() throws JSONException {
        Toast.makeText(getApplicationContext(), ""+arr, Toast.LENGTH_LONG).show();
        final RequestQueue rq = Volley.newRequestQueue(Debitnote.this);
        lt = getSharedPreferences(MYPREFER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = lt.edit();
        String t = lt.getString("tok", "");
        t = "Token " + t;

        final String finalT1 = t;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://52.66.240.69:8080/api/finance/DebitCredit/buy/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Debitnote.this, "" + response, Toast.LENGTH_LONG).show();


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
                Log.i("whdbhwbd", " "+arr);
                params.put("all_discount", "0");
                params.put("invoice", idlist.get(p));
                params.put("issue_date", "2018-12-26T19:12:28.327Z");
                params.put("number", "24121997");
                params.put("returnlist", arr.toString());
                params.put("subtotal", "123214");
                params.put("total", "4543666");
                params.put("android", "y");


                params.put("valid_until", "2018-12-26T19:12:28.327Z");

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


    public void save_cr_debit(View view) {

        arr.add(new JSONObject(json3));
        Toast.makeText(getApplicationContext(), ""+arr, Toast.LENGTH_LONG).show();
        // Toast.makeText(getApplicationContext(), ""+idlist.get(p), Toast.LENGTH_LONG).show();

        try {
            final_save_credit();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
