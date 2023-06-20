package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;


import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;


import org.json.JSONObject;


import java.util.ArrayList;

public class GetAllUser extends AppCompatActivity
{
    RecyclerView rv_data;
    //MyAdapter constructer ke through data get karenge
    ArrayList<String> al_name,al_email,al_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_user);

        rv_data = (RecyclerView) findViewById(R.id.rv_data);
        al_name = new ArrayList();
        al_email = new ArrayList();
        al_gender = new ArrayList();

        getUserData();
    }

    //it will get the users data thru Volley Library
    void getUserData()
    {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest =  new StringRequest(
                Request.Method.POST,
                "http://192.168.1.17/JsonDemo.php/getAllUsersData",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {

                        //Toast.makeText(GetAllUser.this, ""+response, Toast.LENGTH_SHORT).show();
                        //parse json bcz responce come in the form of JSON

                        if(response.equals("fail"))
                        {
                            Toast.makeText(GetAllUser.this, "Error occured", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {

                            try
                            {
                                   String name ,email  ,gender ;
                                   // Log.e( "onResponse: ",response);
                                    JSONObject jsonObject = new JSONObject(response);

                                   //Log.d("JSONObject = ", jsonObject.toString());
                                   JSONArray jsonArray =  jsonObject.getJSONArray("{responce_obj}");

                                    //traverse the json array ek ek karke print karega

                                    for (int i=0; i<jsonArray.length(); i++)
                                    {
                                        //{/**JSON OBJECT
                                        //  "responce_obj":
                                        //       [  /**ARRAY
                                                //**JSONOBJECT
                                                //{"name":"shree","email":"shree@gmail.com","password":"123","gender":"male"},
                                                // {"name":"prachi","email":"prachi@gmail.com","password":"123","gender":"female"},
                                        //        ]
                                        // }


                                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                        //store in name,email,gender
                                        name=jsonObject1.getString("name");
                                        email=jsonObject1.getString("email");
                                        gender=jsonObject1.getString("gender");


                                        //getting specific key values
//                                        Log.d("name = ", jsonObject1.getString("name"));
//                                        Log.d("email = ", jsonObject1.getString("email"));
//                                        Log.d("gender = ", jsonObject1.getString("gender"));

                                        //add in arrayList
                                        al_name.add(name);
                                        al_email.add(email);
                                        al_gender.add(gender);
                                    }

                                    MyAdpter myAdpter = new MyAdpter(al_name,al_email,al_gender);
                                        rv_data.setLayoutManager(new LinearLayoutManager(GetAllUser.this));
                                        rv_data.setAdapter(myAdpter);
                                }
                                   catch (Exception e)
                                   {
                                       e.printStackTrace();
                                   }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                        Toast.makeText(GetAllUser.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(stringRequest);

    }
}