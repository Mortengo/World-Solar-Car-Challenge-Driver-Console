package com.deakin.university.diverconsole;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class GetWeatherInformation extends AsyncTask<String, Void, String> {

    private static DecimalFormat df1 = new DecimalFormat("#.#");

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        HttpURLConnection urlConnection;
        URL url;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream input = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);

            int data = reader.read();
            while (data != -1) {
                char c = (char) data;
                result += c;
                data = reader.read();
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject weather = new JSONObject(jsonObject.getString("main"));
            double temperature = Double.parseDouble(weather.getString("temp"));

            String city = jsonObject.getString("name");

            MainActivity.weatherLocation.setText(city);
            MainActivity.weatherTemperature.setText(df1.format(temperature) + "℃");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
