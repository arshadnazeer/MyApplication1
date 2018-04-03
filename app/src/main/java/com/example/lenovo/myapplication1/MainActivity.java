package com.example.lenovo.myapplication1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.myapplication1.Adapter.Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    String text;
    private String url = "https://api.github.com/users/_username_/repos";
    private String value2 = "_username_";
    String nameValue;
    String urlValue;
    ListView myListView;
    Adapter adapter;
    ArrayList<Model>nameArraylist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.cliick);
        myListView = (ListView) findViewById(R.id.list);

        adapter = new Adapter(this,nameArraylist);
        myListView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Button Clicked","click");

                EditText editText = (EditText)findViewById(R.id.name);
                text = editText.getText().toString();

                sendAndRequestResponse();

            }
        });

    }

    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        StringRequest getRequest = new StringRequest(Request.Method.GET, url.replace(value2,text),
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response

                        Log.e("RESPONSE",""+response);

                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            Model model;
                            for (int i = 0; i < jsonarray.length(); i++) {
                                model = new Model();

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                model.setName(jsonobject.getString("name"));
                                model.setUrl(jsonobject.getString("url"));

                                nameValue = jsonobject.getString("name");
                                urlValue = jsonobject.getString("url");

                                nameArraylist.add(model);

                                Log.e("PRINTNAME0",""+nameValue);
                                Log.e("PRINTNAME0",""+urlValue);

                            }
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//

                        Log.d("Response", response);
                    }

                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", ""+error);
                    }
                }
        );

        Log.d("PRINT","TEXTVALUE :"+url);
        mRequestQueue.add(getRequest);
    }

}


