package com.deakin.university.diverconsole;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextView temp = null;
    private SensorManager ManageSensor = null;
    private Button show = null;
    private Button hide = null;
    private Sensor TemSensor = null;
    private float temperatureC;

    public float getTemperatureC() {
        return getFloat(temperatureC) ;
    }
    public float getFloat(float value)
    {
        return (float)(Math.round(value * 10)) / 10;
    }
    public void setTemperatureC(float temperatureC)
    {
        this.temperatureC = temperatureC;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp = (TextView)findViewById(R.id.Tem);
        //get the sensor server
        ManageSensor = (SensorManager)getSystemService(SENSOR_SERVICE);
        //give the sensor type of
        TemSensor = ManageSensor.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        show = (Button)findViewById(R.id.showTem);
        hide = (Button)findViewById(R.id.hideTem);
        final SensorEventListener mSensorEventListner = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE)
                {
                    float Temperature = event.values[0];
                    setTemperatureC(Temperature);
                    temp.setText(String.valueOf(getTemperatureC() + "℃"));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        show.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                ManageSensor.registerListener(mSensorEventListner, TemSensor,SensorManager.SENSOR_DELAY_NORMAL);
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                temp.setVisibility(View.VISIBLE);
            }
        });
        hide.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ManageSensor.unregisterListener(mSensorEventListner,TemSensor);
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                temp.setVisibility(View.INVISIBLE);
            }
        });

    }

}
