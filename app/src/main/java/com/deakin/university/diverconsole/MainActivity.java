package com.deakin.university.diverconsole;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
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

import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    public static TextView speedTextView, batteryTextView, distanceTraveled, ambientTemperature, weatherLocation, weatherTemperature;

    private static final String GET_SPEED_DATA_API = "http://10.0.2.2:2000/data/speed";
    private static final String GET_BATTERY_DATA_API = "http://10.0.2.2:2000/data/battery";
    private static final String GET_DISTANCE_TRAVELED_DATA_API = "http://10.0.2.2:2000/data/distanceTraveled";
    private static final String OPEN_WEATHER_API_LINK = "https://api.openweathermap.org/data/2.5/weather?lat=";
    private static final String SPEED_KEY_TAG = "speed";
    private static final String BATTERY_KEY_TAG = "battery";
    private static final String DISTANCE_TRAVELED_TAG = "distanceTraveled";
    private static final String LONGITUDE_TAG = "&lon=";
    private static final String UNITS_METRICS_TAG = "&units=metric";
    private static final String WEATHER_API_KEY_TAG = "&appid=54adf57bbc67acd54ea5288d3964f297";
    private static final String SPEED_TAG = "Current Speed: ";
    private static final String BATTERY_LEVEL_TAG = "Battery Level: ";

    private SensorManager sensorManager;
    private Sensor ambientTemperatureSensor;

    GetAmbientTemp getAmbientTemp = new GetAmbientTemp(MainActivity.this);



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
                mHandler.postDelayed(this, 500);
            }
            else if (speedListCount <= readData.getSpeedList().size() - 1 && currentSpeed > Integer.valueOf(readData.getSpeedList().get(speedListCount))){
                currentSpeed--;
                Log.d(SPEED_TAG, String.valueOf(currentSpeed));
                speedTextView.setText(getString(R.string.speed_unit, currentSpeed));
                mHandler.postDelayed(this, 500);
            }
            else if (speedListCount < readData.getSpeedList().size() - 1 && currentSpeed == Integer.valueOf(readData.getSpeedList().get(speedListCount))){
                speedListCount++;
                mHandler.postDelayed(this, 500);
            }
            else if (speedListCount == readData.getSpeedList().size() - 1) {
                speedListCount=0;
                mHandler.postDelayed(this, 500);
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
                mHandler.postDelayed(this, 1500);
            }
            else if (batteryListCount <= readData.getBatteryList().size() - 1 && currentBattery > Integer.valueOf(readData.getBatteryList().get(batteryListCount))){
                currentBattery--;
                Log.d(BATTERY_LEVEL_TAG, String.valueOf(currentBattery));
                batteryTextView.setText(getString(R.string.battery_unit, currentBattery));
                prg.setProgress(currentBattery);
                mHandler.postDelayed(this, 1500);
            }
            else if (batteryListCount < readData.getBatteryList().size() - 1 && currentBattery == Integer.valueOf(readData.getBatteryList().get(batteryListCount))) {
                batteryListCount++;
                mHandler.postDelayed(this, 1500);
            }
            else if (batteryListCount == readData.getBatteryList().size() - 1) {
                batteryListCount = 0;
                mHandler.postDelayed(this, 1500);
            }
            Log.d("List Count: ", String.valueOf(batteryListCount));
        }
    };

    public Runnable UpdateDistanceTraveled = new Runnable() {
        int distance = 0;

        @Override
        public void run() {
            distanceTraveled = findViewById(R.id.distanceTraveled);

            if (distance < Integer.valueOf(readData.distanceTraveledList.get(0))) {
                distanceTraveled.setText(getString(R.string.distance_traveled, distance));
                distance++;
                mHandler.postDelayed(this, 5000);
            }
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

        getWeatherInformation.execute(OPEN_WEATHER_API_LINK + latitude + LONGITUDE_TAG + longitude + UNITS_METRICS_TAG + WEATHER_API_KEY_TAG );
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speedTextView = findViewById(R.id.speedTextView);
        batteryTextView = findViewById(R.id.batteryPowerValue);

        //Queue for reading data from API to retrieve simulator data
        readData.mQueue = Volley.newRequestQueue(this);

        //Gets data from localhost API
        try {
            readData.jsonParse(GET_SPEED_DATA_API, SPEED_KEY_TAG);
            readData.jsonParse(GET_BATTERY_DATA_API, BATTERY_KEY_TAG);
            readData.jsonParse(GET_DISTANCE_TRAVELED_DATA_API, DISTANCE_TRAVELED_TAG);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Update the date and time on the screen
        updateDateAndTime.showDateAndTime();

        //Get the sensor service
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        //Give the sensor type i.e. Ambient Temperature
        ambientTemperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        //Register the listener and display ambient temperature on the screen
        sensorManager.registerListener(getAmbientTemp.mSensorEventListener, ambientTemperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler = new Handler();
        mHandler.postDelayed(UpdateSpeed, 100);
        mHandler.postDelayed(UpdateBattery, 100);
        mHandler.postDelayed(UpdateDistanceTraveled, 100);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(UpdateSpeed);
        mHandler.removeCallbacks(UpdateBattery);
        mHandler.postDelayed(UpdateDistanceTraveled, 100);
    }
}