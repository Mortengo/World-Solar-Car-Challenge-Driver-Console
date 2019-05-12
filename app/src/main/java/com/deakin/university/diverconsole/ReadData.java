package com.deakin.university.diverconsole;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadData {

    ArrayList<String> speedList = new ArrayList<>();
    ArrayList<String> batteryList = new ArrayList<>();

    // Reads JSON file and parses it to an ArrayList
    public void parseJson(Context myContext, String jsonFile, String keySearch) {
        AssetManager manager = myContext.getAssets();
        String json;

        try {
            InputStream is = manager.open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray= new JSONArray(json);

            for (int i=0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String object = obj.getString(keySearch);

                if (jsonFile == "speed.json") {
                    speedList.add(object);
                }
                if (jsonFile == "battery.json") {
                    batteryList.add(object);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getSpeedList() {
        return speedList;
    }

    public ArrayList<String> getBatteryList() {
        return batteryList;
    }
}
