package com.deakin.university.diverconsole;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class UpdateDateAndTime extends AsyncTask<String, Void, String> {
    private Activity mActivity;

    public UpdateDateAndTime(Activity activity) {
        this.mActivity = activity;
    }

    public void showDateAndTime() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while(!isInterrupted()) {
                        Thread.sleep(1000);
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView DateAndTime = mActivity.findViewById(R.id.DateAndTime);
                                long currentTimeMillis = System.currentTimeMillis();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy\nhh:mm:ss a");
                                String DateTime = simpleDateFormat.format(currentTimeMillis);
                                DateAndTime.setText(DateTime);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    System.out.println("Thread Interrupted");
                }
            }
        };
        thread.start();
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}
