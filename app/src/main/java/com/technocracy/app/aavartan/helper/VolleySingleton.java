package com.technocracy.app.aavartan.helper;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Abhishek on 24-06-aavartan2016.
 */
public class VolleySingleton
{
    private static VolleySingleton sInstance = null;
    private RequestQueue mrequestQueue;
StringRequest mStringRequest;
    public static VolleySingleton getInstance()
    {
        if(sInstance==null) {
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }

    private VolleySingleton()
    {
        try {
            mrequestQueue = Volley.newRequestQueue(AppController.mInstance);
            mStringRequest = new StringRequest(Request.Method.GET, "http://php.net/", new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Toast.makeText(AppController.mInstance, s, Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(AppController.mInstance, "Error Occurs " + volleyError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }catch (NullPointerException e){}
    }
    public RequestQueue getRequestQueue(){
     return mrequestQueue;
    }
}
