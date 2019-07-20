package com.abdo.hp.task;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import static android.content.Context.ALARM_SERVICE;

public class TimerService {

    void setTime(Context context){

        Intent myIntent =new Intent(context,MyService.class);
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent=PendingIntent.getService(context,0,myIntent,0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        5 * 1000, pendingIntent);

    }

}
