package com.deakin.university.diverconsole;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> speedList = new ArrayList<>();

    TextView speedTextView;

    Handler mHandler = new Handler();


    // Iterates through the list and sets the value given.
    public Runnable runnable = new Runnable() {

        int listCount = 0;
        int currentSpeed = 0;

        @Override
        public void run() {

            //Check put in place as speedList.size() returns items in the list not the index of the array. Hence we need to minus one from speedList.size()
            if (listCount <= speedList.size() - 1 && currentSpeed < Integer.valueOf(speedList.get(listCount))){
                currentSpeed++;
                Log.d("Current Speed: ", String.valueOf(currentSpeed));
                speedTextView.setText(getString(R.string.speed_unit, currentSpeed));
                mHandler.postDelayed(this, 100);
            }
            else if (listCount <= speedList.size() - 1 && currentSpeed > Integer.valueOf(speedList.get(listCount))){
                currentSpeed--;
                Log.d("Current Speed: ", String.valueOf(currentSpeed));
                speedTextView.setText(getString(R.string.speed_unit, currentSpeed));
                mHandler.postDelayed(this, 100);
            }
            else if (listCount < speedList.size() - 1 && currentSpeed == Integer.valueOf(speedList.get(listCount))){
                listCount++;
                mHandler.postDelayed(this, 100);
            }
            else if (listCount == speedList.size() - 1) {
                listCount=0;
                mHandler.postDelayed(this, 100);
            }

            Log.d("List Count: ", String.valueOf(listCount));
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speedTextView = findViewById(R.id.speedTextView);

        getJSON();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler = new Handler();
        mHandler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(runnable);
    }

    // Reads JSON file and parses it to an ArrayList
    public void getJSON() {
        String json;

        try {
            InputStream is = getAssets().open("speed.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray= new JSONArray(json);

            for (int i=0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                String speed = obj.getString("speed");
                speedList.add(speed);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}