package com.deakin.university.diverconsole;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    static TextView placetext;
    static TextView temptext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placetext = (TextView) findViewById(R.id.location);
        temptext = (TextView) findViewById(R.id.weather);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        double lat = location.getLatitude();
        double lon = location .getLongitude();

        functions _function = new functions();
        // _function.execute("https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=6bd9d37d838c6880b4f7be717a002f49");
        //_function.execute("http://api.openweathermap.org/data/2.5/weather?lat="+ lat + "&lon=" + lon +"&appid=54adf57bbc67acd54ea5288d3964f297");
        _function.execute("https://api.openweathermap.org/data/2.5/weather?q=Melbourne&appid=54adf57bbc67acd54ea5288d3964f297");

    }
}
