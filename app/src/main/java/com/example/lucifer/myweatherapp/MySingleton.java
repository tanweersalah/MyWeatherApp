package com.example.lucifer.myweatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Lucifer on 11-02-2018.
 */

public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mcontext;

    private MySingleton(Context context){
        mcontext= context;
        requestQueue = getRequestQueue();


    }
    public RequestQueue getRequestQueue(){
        if (requestQueue== null){
            requestQueue= Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized MySingleton getInstance(Context context){
        if (mInstance==null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }
    public  void addrequest(Request request){requestQueue.add(request);}




}

