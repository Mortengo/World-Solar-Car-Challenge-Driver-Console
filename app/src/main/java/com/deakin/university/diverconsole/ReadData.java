package com.deakin.university.diverconsole;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReadData {

    public RequestQueue mQueue;
    ArrayList<String> speedList = new ArrayList<>();
    ArrayList<String> batteryList = new ArrayList<>();
    ArrayList<String> distanceTraveledList = new ArrayList<>();


    public void jsonParse(String URL, final String keySearch) throws InterruptedException {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String data = jsonObject.getString(keySearch);
                        if (keySearch == "speed") {
                            speedList.add(data);
                        }
                        if (keySearch == "battery") {
                            batteryList.add(data);
                        }
                        if (keySearch == "distanceTraveled") {
                            distanceTraveledList.add(data);
                            System.out.println(data);
                            System.out.println(distanceTraveledList);
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
        Thread.sleep(2000);
    }


    public ArrayList<String> getSpeedList() {
        return speedList;
    }

    public  ArrayList<String> getBatteryList() {
        return batteryList;
    }
}

