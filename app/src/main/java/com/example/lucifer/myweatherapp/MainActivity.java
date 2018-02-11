package com.example.lucifer.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
        Button button;
        EditText editText;
        TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        editText= (EditText)findViewById(R.id.editText);
        result = (TextView)findViewById(R.id.result);
        final String url1="http://api.openweathermap.org/data/2.5/weather?q=";
        final String url2= "&appid=515f85cecbe32b71aae33fdac6de0f79";




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityname = editText.getText().toString().trim();

                String myurl = url1+cityname+url2;
                JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, myurl, null, new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject response) {

                        try {

                            JSONObject weather = (JSONObject) response.get("main" );
                            String maxtem = weather.getString("temp_max");
                            String mintem = weather.getString("temp_min");
                            String discription = "";
                            JSONArray disc = response.getJSONArray("weather");
                            for (int i=0;i<disc.length();i++){
                                JSONObject d =disc.getJSONObject(i);
                                discription = d.getString("description");

                            }
                            result.setText("It will be "+discription +"\n"+"Max Temperature : "+ maxtem+"\n"+"Min Temperature : "+ mintem);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                MySingleton.getInstance(MainActivity.this).addrequest(jsonObjectRequest);








            }
        });





















    }
}
