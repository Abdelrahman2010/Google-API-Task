package Controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.abdo.hp.task.CustList;
import com.abdo.hp.task.Home;
import com.abdo.hp.task.ImportantDetails;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import Models.Events;


public class EventsControl {

    RequestQueue requestQueue;
    ArrayList<String> kind;

    ArrayList<String> humidity;
    ArrayList<String> Temb;
    ArrayList<String> IDs = new ArrayList<>();


    public void GetAllEvents(String Email, final Context context, final ListView myListView, final ArrayList<String> humidity, final ArrayList<String> Temb) {


        requestQueue = Volley.newRequestQueue(context);
        kind = new ArrayList<>();

        final boolean[] update = {false};

        ImportantDetails.progressbar++;


        int count = 0;

        String showURL = "https://www.googleapis.com/calendar/v3/calendars/" + Email + "/events?key=" + ImportantDetails.API_KEY;
        ProgressDialog loading = null;
        if (ImportantDetails.progressbar == 1) {
            loading = ProgressDialog.show(context, "downloading ... ", "waiting", true, false);
        }

        final ProgressDialog finalLoading = loading;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, showURL
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                final int[] count = {0};
                try {


                    JSONArray items = response.getJSONArray("items");
                    //        rar.setText(items.toString());
                    ArrayList<Events> myEvents = new ArrayList<Events>();
                    for (int i = 0; i < items.length(); i++) {


                        JSONObject item = items.getJSONObject(i);

                        String kindd = item.getString("kind");
                        kind.add(kindd);
                        String ID = item.getString("id");

                        if (IDs.contains(ID)) {

                        } else {

                            IDs.add(ID);
                            update[0] = true;
                        }
                        String summaryy = item.getString("summary");
                        JSONObject Start = item.getJSONObject("start");
                        String start = Start.getString("dateTime");
                        String statuss = item.getString("status");

                        JSONObject creator = item.getJSONObject("creator");
                        String email = creator.getString("email");


                        String timesindex = GetDate(start);
                        myEvents.add(new Events(summaryy, start, statuss, timesindex, email, ID));


                    }


                    //  dateTime =sort_accourding_to_date(dateTime);


                    Collections.sort(myEvents);

                    if (update[0] == true) {
                        CustList customList = new CustList((Activity) context, kind, myEvents, Temb, humidity);
                        myListView.setAdapter(customList);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(getActivity(),"تم ",Toast.LENGTH_LONG).show();

                if (ImportantDetails.progressbar == 1) {
                    finalLoading.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //    Toast.makeText(BrowesScreen.this, "Not Connection", Toast.LENGTH_LONG).show();
                finalLoading.dismiss();
                //    GetAllAdds();

            }

        });

        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(jsonObjectRequest);
        //  swipeLayout.setRefreshing(false);

    }


    public void Get16DayWaether(final String Email, final Context context, final ListView myListView) {


        requestQueue = Volley.newRequestQueue(context);
        humidity = new ArrayList<>();
        Temb = new ArrayList<>();


        int count = 0;

        String showURL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Cairo&cnt=16&APIKEY=0bfb5f9dbabc8d572d4904a52d2e439d";
        final ProgressDialog loading;
        //   loading = ProgressDialog.show(context, "downloading ... ", "waiting", true, false);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, showURL
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String FirstImagePath;

                final int[] count = {0};
                try {


                    JSONArray items = response.getJSONArray("list");
                    //        rar.setText(items.toString());
                    for (int i = 0; i < items.length(); i++) {


                        JSONObject item = items.getJSONObject(i);


                        FirstImagePath = item.getString("humidity");
                        humidity.add(FirstImagePath);
                        JSONObject Start = item.getJSONObject("temp");
                        FirstImagePath = Start.getString("day");
                        Temb.add(FirstImagePath);


                    }


                    GetAllEvents(Email, context, myListView, humidity, Temb);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(getActivity(),"تم ",Toast.LENGTH_LONG).show();

                //           loading.dismiss();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //    Toast.makeText(BrowesScreen.this, "Not Connection", Toast.LENGTH_LONG).show();
                //           loading.dismiss();
                //    GetAllAdds();

            }

        });

        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(jsonObjectRequest);
        //  swipeLayout.setRefreshing(false);

    }


    String GetDate(String CalenderDate) {

        Calendar c = Calendar.getInstance();

        String Today = c.get(Calendar.YEAR) + "-"
                + c.get(Calendar.MONTH)
                + "-" + c.get(Calendar.DAY_OF_MONTH);


        String[] split = CalenderDate.split("T");
        String CalendarDate = split[0];
        String returnedDate = "not";

        Calendar startCalemder = Calendar.getInstance();
        startCalemder.setTime(new Date());
        startCalemder.add(Calendar.DATE, 1);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(new Date());
        endCalendar.add(Calendar.DATE, 16);

        //loop over day by day
        int i = 0;
        for (; startCalemder.compareTo(endCalendar) <= 0;
             startCalemder.add(Calendar.DATE, 1)) {
            startCalemder.get(Calendar.YEAR); //shows year
            startCalemder.get(Calendar.MONTH); //shows month
            startCalemder.get(Calendar.DAY_OF_MONTH); //shows day


            int realmonth = startCalemder.get(Calendar.MONTH) + 1;
            String months = Integer.toString(realmonth);
            if (realmonth > 12) {

                realmonth = 1;
            }
            if (realmonth < 10) {

                months = "0" + months;
            }

            String nextday = startCalemder.get(java.util.Calendar.YEAR) + "-"
                    + months
                    + "-" + startCalemder.get(java.util.Calendar.DAY_OF_MONTH);

            if (CalendarDate.equals(nextday)) {


                returnedDate = Integer.toString(i);
                break;

            }

            i++;

        }


        return returnedDate;
    }





}
