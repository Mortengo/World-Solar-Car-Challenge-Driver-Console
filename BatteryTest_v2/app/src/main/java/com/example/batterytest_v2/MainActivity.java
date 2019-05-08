package com.example.batterytest_v2;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> batteryList = new ArrayList<>();
    TextView batteryTextView;
    Handler mHandler = new Handler();

    public Runnable runnable = new Runnable() {


        int listCount = 0;
        int currentBattery = 0;
        ProgressBar prg;


        @Override
        public void run() {


            batteryTextView = findViewById(R.id.batterypowerValue);
            prg = findViewById(R.id.progressbar);

            //Check put in place as speedList.size() returns items in the list not the index of the array. Hence we need to minus one from speedList.size()
            if (listCount <= batteryList.size() - 1 && currentBattery < Integer.valueOf(batteryList.get(listCount))) {
                currentBattery++;
                Log.d("Current Battery: ", String.valueOf(currentBattery));
                batteryTextView.setText(Integer.toString(currentBattery));
                prg.setProgress(currentBattery);
                mHandler.postDelayed(this, 100);
            }
            else if (listCount <= batteryList.size() - 1 && currentBattery > Integer.valueOf(batteryList.get(listCount))) {
                currentBattery--;
                Log.d("Current Battery: ", String.valueOf(currentBattery));
                batteryTextView.setText(Integer.toString(currentBattery));
                prg.setProgress(currentBattery);
                mHandler.postDelayed(this, 100);
            }
            else if (listCount < batteryList.size() - 1 && currentBattery == Integer.valueOf(batteryList.get(listCount))) {
                listCount++;
                mHandler.postDelayed(this, 100);
            }
            else if (listCount == batteryList.size() - 1) {
                listCount = 0;
                mHandler.postDelayed(this, 100);
            }
            Log.d("List Count: ", String.valueOf(listCount));
        }
    };

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


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getJSON();
    }


        // Reads JSON file and parses it to an ArrayList
        public void getJSON() {
            String json;

            try {
                InputStream is = getAssets().open("battery.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                JSONArray jsonArray= new JSONArray(json);

                for (int i=0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    String speed = obj.getString("battery");
                    batteryList.add(speed);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
}






/*
        //Decrease Method
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                battery = battery - 5;
                prg.setProgress(battery);
                batteryTextView.setText(Integer.toString(battery) + " %");

                if (battery > 90 && battery <= 100){
                    imagebattery.setImageResource(R.drawable.battery100);
                }
                else if(battery > 75 && battery <= 90) {
                    imagebattery.setImageResource(R.drawable.battery90);
                }
                else if(battery > 50 && battery <= 75) {
                    imagebattery.setImageResource(R.drawable.battery75);
                }
                else if(battery > 25 && battery <= 50) {
                    imagebattery.setImageResource(R.drawable.battery50);
                }
                else if(battery > 15 && battery <= 25) {
                    imagebattery.setImageResource(R.drawable.battery25);
                }
                else if(battery > 5 && battery <= 15) {
                    imagebattery.setImageResource(R.drawable.battery15);
                }
                else if(battery > 0 && battery <= 5) {
                    imagebattery.setImageResource(R.drawable.battery5);
                }
                else if(battery <= 0) {
                    imagebattery.setImageResource(R.drawable.battery0);
                }
                else if(battery > 100){
                    imagebattery.setImageResource(R.drawable.battery100);
                }
            }
        });

        //Increase Method
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                battery = battery + 5;
                prg.setProgress(battery);
                batteryTextView.setText(Integer.toString(battery) + " %");

                if (battery > 90 && battery <= 100){
                    imagebattery.setImageResource(R.drawable.battery100);
                }
                else if(battery > 75 && battery <= 90) {
                    imagebattery.setImageResource(R.drawable.battery90);
                }
                else if(battery > 50 && battery <= 75) {
                    imagebattery.setImageResource(R.drawable.battery75);
                }
                else if(battery > 25 && battery <= 50) {
                    imagebattery.setImageResource(R.drawable.battery50);
                }
                else if(battery > 15 && battery <= 25) {
                    imagebattery.setImageResource(R.drawable.battery25);
                }
                else if(battery > 5 && battery <= 15) {
                    imagebattery.setImageResource(R.drawable.battery15);
                }
                else if(battery > 0 && battery <= 5) {
                    imagebattery.setImageResource(R.drawable.battery5);
                }
                else if(battery <= 0) {
                    imagebattery.setImageResource(R.drawable.battery0);
                }
                else if(battery > 100){
                    imagebattery.setImageResource(R.drawable.battery100);
                }

            }
        });




    }
}
*/