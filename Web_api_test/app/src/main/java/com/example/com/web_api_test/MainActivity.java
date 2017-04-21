package com.example.com.web_api_test;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    static final String API_URL = "http://couponsandgiveawaysapi.azurewebsites.net/api/login/login";
    static final String API_URL_trial = "http://google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button queryButton = (Button) findViewById(R.id.button);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveFeedTask().execute();
            }
        });

    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {

        }

        protected String doInBackground(Void... urls)
        {
            String ret = new String();
            HttpWrapper ob = new HttpWrapper();
            try {
                ret = ob.Post(API_URL,"username","password");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                ret = ob.Get(API_URL_trial);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ret;
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
        }
    }
}