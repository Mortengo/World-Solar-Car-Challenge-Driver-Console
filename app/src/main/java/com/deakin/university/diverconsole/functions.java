package com.deakin.university.diverconsole;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class functions extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        HttpURLConnection urlConnection = null;
        URL url;

        try
        {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream input = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            int data = reader.read();
            while (data != -1)
            {
                char c = (char) data;
                result += c;
                data = reader.read();
            }
            return  result;
        }
        catch(Exception e)
        {
               e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try
        {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject weather = new JSONObject(jsonObject.getString("main"));
            double temperature = Double.parseDouble(weather.getString("temp"));
            int tempInt = (int)(temperature - 273.15);

            String city = jsonObject.getString("name");

            MainActivity.temptext.setText(String.valueOf(tempInt) + "℃");
            MainActivity.placetext.setText(city);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
