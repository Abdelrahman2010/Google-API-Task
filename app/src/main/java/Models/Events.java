package Models;

import android.support.annotation.NonNull;

public class Events implements Comparable<Events>{

    String summary;
    String dateTime;
    String States;
    String CreatorEmail;
    String TimeState;
    String ID;






    public Events(String summary, String dateTime, String states, String timeState,String CreatorEmail,String ID) {
        this.summary = summary;
        this.dateTime = dateTime;
        this.States = states;
        this.CreatorEmail = CreatorEmail;
        this.TimeState = timeState;
        this.ID = ID;
    }


    public String getsummary() {
        return summary;
    }

    public void setsummary(String time) {
        this.summary = time;
    }



    public String getdateTime() {
        return dateTime;
    }

    public void setdateTime(String details) {
        dateTime = details;
    }



    public String getStates() {
        return States;
    }

    public void setStates(String date) {
        States = date;
    }









    public String getTimeState() {
        return TimeState;
    }

    public void setTimeState(String timeState) {
        TimeState = timeState;
    }

    public String getCreatorEmail() {
        return CreatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        CreatorEmail = creatorEmail;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    @Override
    public int compareTo(@NonNull Events o) {
        return this.getdateTime().compareTo(o.getdateTime());

    }



}
