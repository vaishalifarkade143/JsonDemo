package com.example.jsondemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity
{
    EditText log_email,log_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log_email = (EditText) findViewById(R.id.log_email);
        log_pass = (EditText) findViewById(R.id.log_pass);
    }
    public  void openLogin(View view)
    {
        String email = log_email.getText().toString();
        String pass = log_pass.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://192.168.1.17/Jsondemo.php/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(Login.this, ""+response, Toast.LENGTH_SHORT).show();

                        try
                        {
                            String name=null,email=null,gender=null;
                            //to parse json
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.optJSONArray("responce_obj");

                            for (int i=0 ; i< jsonArray.length();i++)
                            {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                name=jsonObject1.optString("name").toString();
                                email=jsonObject1.optString("email").toString();
                                gender=jsonObject1.optString("gender").toString();
                            }

                            Toast.makeText(Login.this, "Name:"+name+"\nEmail:"+email+"\nGender:"+gender, Toast.LENGTH_SHORT).show();

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                // getParams() method tranfer the values on api
                HashMap <String,String> hm = new HashMap<>();
                hm.put("key_email",email);
                hm.put("key_pass",pass);
                return hm;

            }
        };

        requestQueue.add(stringRequest);
    }
    public void openGetAllUserData(View view)
    {
        startActivity(new Intent(Login.this, GetAllUser.class));
    }
}