package com.example.com.web_api_test;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.goebl.david.Response;
import com.goebl.david.Webb;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class MainActivity extends AppCompatActivity {

    EditText emailText;
    TextView responseView;
    ProgressBar progressBar;


    // static final String API_KEY = "USE_YOUR_OWN_API_KEY";
    //static final String API_URL = "http://couponsandgiveawaysapi.azurewebsites.net/api/login/login";
    static final String API_URL = "https://www.in.yahoo.com";
    Activity pass = this;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button queryButton = (Button) findViewById(R.id.button);
        queryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new getTask(API_URL,pass).execute();
            }
        });

    }
}

class getTask extends AsyncTask<Void, Void, String>
{
    private String getUrl;
    private Activity activity;
    getTask(String _getUrl, Activity _activity)
    {
        this.getUrl = _getUrl;
        this.activity = _activity;
    }

    private Exception exception;

    protected String doInBackground(Void... urls)
    {
        String j = "";
        try
        {
            Webb webb = Webb.create();
            webb.setBaseUri(getUrl);
            j = webb.get(getUrl).asString().getBody();
        }
        catch (Exception e)
        {

        }
        return j;
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        TextView t = (TextView) activity.findViewById(R.id.textView);
        t.setText(response);
    }
}