package com.deakin.university.diverconsole;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static TextView speedTextView, batteryTextView, distanceTraveled, ambientTemperature, weatherLocation, weatherTemperature;


    private static final String SPEED_TAG = "Current Speed: ";
    private static final String BATTERY_LEVEL_TAG = "Battery Level: ";


    UpdateDateAndTime updateDateAndTime = new UpdateDateAndTime(MainActivity.this);

    ReadData readData = new ReadData();

    Handler mHandler = new Handler();

    // Iterates through the list and sets the value given.
    public Runnable UpdateSpeed = new Runnable() {

        int speedListCount = 0;
        int currentSpeed = 0;

        @Override
        public void run() {

            //Check put in place as speedList.size() returns items in the list not the index of the array. Hence we need to minus one from speedList.size()
            if (speedListCount <= readData.getSpeedList().size() - 1 && currentSpeed < Integer.valueOf(readData.getSpeedList().get(speedListCount))){
                currentSpeed++;
                Log.d(SPEED_TAG, String.valueOf(currentSpeed));
                speedTextView.setText(getString(R.string.speed_unit, currentSpeed));
                mHandler.postDelayed(this, 100);
            }
            else if (speedListCount <= readData.getSpeedList().size() - 1 && currentSpeed > Integer.valueOf(readData.getSpeedList().get(speedListCount))){
                currentSpeed--;
                Log.d(SPEED_TAG, String.valueOf(currentSpeed));
                speedTextView.setText(getString(R.string.speed_unit, currentSpeed));
                mHandler.postDelayed(this, 100);
            }
            else if (speedListCount < readData.getSpeedList().size() - 1 && currentSpeed == Integer.valueOf(readData.getSpeedList().get(speedListCount))){
                speedListCount++;
                mHandler.postDelayed(this, 100);
            }
            else if (speedListCount == readData.getSpeedList().size() - 1) {
                speedListCount=0;
                mHandler.postDelayed(this, 100);
            }
        }
    };

    public Runnable UpdateBattery = new Runnable() {


        int batteryListCount = 0;
        int currentBattery = 0;
        ProgressBar prg;


        @Override
        public void run() {

            prg = findViewById(R.id.progressbar);

            if (batteryListCount <= readData.getBatteryList().size() - 1 && currentBattery < Integer.valueOf(readData.getBatteryList().get(batteryListCount))){
                currentBattery++;
                Log.d(BATTERY_LEVEL_TAG, String.valueOf(currentBattery));
                batteryTextView.setText(getString(R.string.battery_unit, currentBattery));
                prg.setProgress(currentBattery);
                mHandler.postDelayed(this, 100);
            }
            else if (batteryListCount <= readData.getBatteryList().size() - 1 && currentBattery > Integer.valueOf(readData.getBatteryList().get(batteryListCount))){
                currentBattery--;
                Log.d(BATTERY_LEVEL_TAG, String.valueOf(currentBattery));
                batteryTextView.setText(getString(R.string.battery_unit, currentBattery));
                prg.setProgress(currentBattery);
                mHandler.postDelayed(this, 100);
            }
            else if (batteryListCount < readData.getBatteryList().size() - 1 && currentBattery == Integer.valueOf(readData.getBatteryList().get(batteryListCount))) {
                batteryListCount++;
                mHandler.postDelayed(this, 100);
            }
            else if (batteryListCount == readData.getBatteryList().size() - 1) {
                batteryListCount = 0;
                mHandler.postDelayed(this, 100);
            }
            Log.d("List Count: ", String.valueOf(batteryListCount));
        }
    };


    public void showWeather(final View view) {
        weatherLocation = findViewById(R.id.weatherLocation);
        weatherTemperature = findViewById(R.id.weatherTemperature);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }


        Location location = locationManager.getLastKnownLocation(provider);

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        GetWeatherInformation getWeatherInformation = new GetWeatherInformation();

        getWeatherInformation.execute("https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&units=metric" + "&appid=54adf57bbc67acd54ea5288d3964f297");
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speedTextView = findViewById(R.id.speedTextView);
        batteryTextView = findViewById(R.id.batteryPowerValue);



        readData.parseJson(getApplicationContext(),"speed.json", "speed");
        readData.parseJson(getApplicationContext(),"battery.json", "battery");

        updateDateAndTime.showDateAndTime();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler = new Handler();
        mHandler.postDelayed(UpdateSpeed, 100);
        mHandler.postDelayed(UpdateBattery, 100);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(UpdateSpeed);
        mHandler.removeCallbacks(UpdateBattery);
    }
}