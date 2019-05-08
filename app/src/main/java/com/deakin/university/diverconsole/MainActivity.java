package com.deakin.university.diverconsole;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Thread newthread = new Thread()
                {
                    @Override
                    public void run()
                    {
                        try{
                            while(!isInterrupted())
                            {
                                Thread.sleep(1000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView tem = (TextView) findViewById(R.id.date);
                                        long date = System.currentTimeMillis();
                                        SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yyyy\nhh:mm:ss a");
                                        String s = sdf.format(date);
                                        tem.setText(s);

                                    }
                                });
                            }
                        }
                        catch (InterruptedException e)
                        {}
                    }
                };
                newthread.start();
    }

}
