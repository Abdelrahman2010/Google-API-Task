package com.abdo.hp.task;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

import Controllers.EventsControl;

public class Home extends AppCompatActivity {


    public static ListView myListView ;
    ArrayList<String> AdID;
    ArrayList<String> Addname;
    ArrayList<String> AddPhoto;
    ArrayList<String> AddPrice;
    RequestQueue requestQueue;

    @SuppressLint({"NewApi", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        requestQueue = Volley.newRequestQueue(Home.this);


        myListView = (ListView)findViewById(R.id.myListView);

        Addname = new ArrayList<>();
        AddPhoto = new ArrayList<>();
        AddPrice = new ArrayList<>();
        AdID = new ArrayList<>();



        final String Emails = getIntent().getExtras().getString("Email");


        EventsControl Even =  new EventsControl();
        Even.Get16DayWaether(Emails,Home.this,myListView);

// run each 30 seconds
        Thread t = new Thread() {
            @Override
            public void run() {

                while (!isInterrupted()) {

                    try {


                        Thread.sleep(30000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {

                                    EventsControl Even =  new EventsControl();
                                    Even.Get16DayWaether(Emails,Home.this,myListView);

                                } catch (Exception e) {
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }


        };
        t.start();


//        Calendar c = Calendar.getInstance();
//
//        String TodayW = c.get(java.util.Calendar.YEAR) + "-"
//                + c.get(java.util.Calendar.MONTH)
//                + "-" + c.get(java.util.Calendar.DAY_OF_MONTH);
//
//        Toast.makeText(Home.this, "NOW" +TodayW, Toast.LENGTH_LONG).show();
//
//
//        Calendar startCalemder = Calendar.getInstance();
//        startCalemder.setTime(new Date());
//        startCalemder.add(Calendar.DATE, 1);
//
//        Calendar endCalendar = Calendar.getInstance();
//        endCalendar.setTime(new Date());
//        endCalendar.add(Calendar.DATE, 16);
//
//        //loop over day by day
//        for (; startCalemder.compareTo(endCalendar) <= 0;
//             startCalemder.add(Calendar.DATE, 1)) {
//            startCalemder.get(Calendar.YEAR); //shows year
//            startCalemder.get(Calendar.MONTH); //shows month
//            startCalemder.get(Calendar.DAY_OF_MONTH); //shows day
//
//
//            String Today = startCalemder.get(java.util.Calendar.YEAR) + "-"
//                    + startCalemder.get(java.util.Calendar.MONTH)
//                    + "-" + startCalemder.get(java.util.Calendar.DAY_OF_MONTH);
//
//            Toast.makeText(Home.this, Today, Toast.LENGTH_LONG).show();
//        }

    }



}
