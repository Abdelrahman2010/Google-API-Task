package com.abdo.hp.task;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;



import java.util.Map;

import Controllers.EventsControl;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//        r.play();

//        EventsControl Even =  new EventsControl();
//        Even.GetAllEvents("abdelrahmnmohamed820@gmail.com",getApplicationContext(),Home.myListView);
//
//        TimerService Tim = new TimerService();
//        Tim.setTime(getApplicationContext());
 //       onDestroy();
        return START_STICKY;
    }









    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();




    }
}

