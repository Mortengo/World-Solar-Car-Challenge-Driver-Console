package com.deakin.university.diverconsole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Create these so we can import the values from their respective .xml id's
    ProgressBar prg;
    Button decrease, increase;
    TextView showbattery;
    ImageView imagebattery;
    int battery= 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the values from .xml, to be edited here with .java
        //Default to 50 for battery
        prg=(ProgressBar)findViewById(R.id.progressbar);
        prg.setProgress(battery);
        showbattery=(TextView)findViewById(R.id.batterypowerValue);
        showbattery.setText(Integer.toString(battery) + " %");
        imagebattery=(ImageView)findViewById(R.id.BatteryImage);
        imagebattery.setImageResource(R.drawable.battery50);


        decrease=(Button)findViewById(R.id.decreasebutton);
        increase=(Button)findViewById(R.id.increasebutton);

        //Decrease Method
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                battery = battery - 5;
                prg.setProgress(battery);
                showbattery.setText(Integer.toString(battery) + " %");

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
                showbattery.setText(Integer.toString(battery) + " %");

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
