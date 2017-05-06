package com.example.com.web_api_test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Einstien on 3/5/2017.
 */

public class HttpWrapper
{
    String Get(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null)
            stringBuilder.append(line).append("\n");
        bufferedReader.close();
        urlConnection.disconnect();
        return stringBuilder.toString();
    }

    String Post(String urlString,String userId,String password) throws IOException
    {
        String parameters = String.format("userId=%s&password=%s",userId,password);

        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);

        urlConnection.setRequestMethod("POST");

        OutputStreamWriter w = new OutputStreamWriter(urlConnection.getOutputStream());
        w.write(parameters);
        w.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        while(reader.readLine() != null)
        {
            stringBuilder.append(reader.readLine()).append('\n');
        }
        w.close();
        reader.close();
        urlConnection.disconnect();
        return stringBuilder.toString();
    }
}
