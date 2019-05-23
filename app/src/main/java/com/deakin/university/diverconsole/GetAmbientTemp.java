package com.deakin.university.diverconsole;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class GetAmbientTemp {

    private Activity mActivity;

    public GetAmbientTemp(Activity activity) {
        this.mActivity = activity;
    }

    public SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() ==  Sensor.TYPE_AMBIENT_TEMPERATURE) {
                float Temperature = event.values[0];

                TextView AmbientTemperature = mActivity.findViewById(R.id.ambientTemperature);
                AmbientTemperature.setText(Temperature + "℃");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //NOT IMPLEMENTED
        }
    };
}
