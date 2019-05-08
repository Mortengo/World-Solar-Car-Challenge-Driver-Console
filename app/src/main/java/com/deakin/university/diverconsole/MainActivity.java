package com.deakin.university.diverconsole;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ReadData readData = new ReadData();


    TextView speedTextView;

    Handler speedHandler = new Handler();

    // Iterates through the list and sets the value given.
    public Runnable UpdateSpeed = new Runnable() {

        int listCount = 0;
        int currentSpeed = 0;

        @Override
        public void run() {

            //Check put in place as speedList.size() returns items in the list not the index of the array. Hence we need to minus one from speedList.size()
            if (listCount <= readData.getSpeedList().size() - 1 && currentSpeed < Integer.valueOf(readData.getSpeedList().get(listCount))){
                currentSpeed++;
                Log.d("Current Speed: ", String.valueOf(currentSpeed));
                speedTextView.setText(getString(R.string.speed_unit, currentSpeed));
                speedHandler.postDelayed(this, 100);
            }
            else if (listCount <= readData.getSpeedList().size() - 1 && currentSpeed > Integer.valueOf(readData.getSpeedList().get(listCount))){
                currentSpeed--;
                Log.d("Current Speed: ", String.valueOf(currentSpeed));
                speedTextView.setText(getString(R.string.speed_unit, currentSpeed));
                speedHandler.postDelayed(this, 100);
            }
            else if (listCount < readData.getSpeedList().size() - 1 && currentSpeed == Integer.valueOf(readData.getSpeedList().get(listCount))){
                listCount++;
                speedHandler.postDelayed(this, 100);
            }
            else if (listCount == readData.getSpeedList().size() - 1) {
                listCount=0;
                speedHandler.postDelayed(this, 100);
            }

            Log.d("List Count: ", String.valueOf(listCount));
            Log.d("Speed List Size", String.valueOf(readData.getSpeedList().size()));
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speedTextView = findViewById(R.id.speedTextView);

        readData.parseJson(getApplicationContext(), "speed.json", "speed");
    }

    @Override
    protected void onStart() {
        super.onStart();
        speedHandler = new Handler();
        speedHandler.postDelayed(UpdateSpeed, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        speedHandler.removeCallbacks(UpdateSpeed);
    }
}