package com.makemyaccounting.accounting.api_request;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.makemyaccounting.accounting.Creditnote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class StartVolleyServices {
    Context context;
    HashMap<String, String> params;


    public StartVolleyServices(Context context) {
        this.context = context;
        this.params = params;
    }

    public void save3(String URl, String method, HashMap<String, String> request) throws JSONException {

        final RequestQueue rq = Volley.newRequestQueue(context);


        //  Toast.makeText(context, "" + t, Toast.LENGTH_LONG).show();


        //final String finalT1 = t;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://52.66.240.69:8080/api/finance/invoice/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    JSONArray jarr = jobj.getJSONArray("sale");
                    for (int i = 0; i < jarr.length(); ++i) {

                        JSONObject jtemp = (JSONObject) jarr.get(i);
                        String no = jtemp.getString("number");
                        String id = jtemp.getString("id");
                        ///   plantsList.add(no);
                        // idlist.add(id);

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

                    //     Toast.makeText(getApplicationContext(), responseBody, Toast.LENGTH_LONG).show();
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
                params.put("Authorization", "");
                return params;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        rq.add(stringRequest);
    }
}
