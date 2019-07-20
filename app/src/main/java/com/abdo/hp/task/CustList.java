package com.abdo.hp.task;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Models.Events;

public class CustList extends ArrayAdapter<String> {

    ArrayList<String> kind;
//    ArrayList<String> summary;
//    ArrayList<String> dateTime;
//    ArrayList<String> States;
//
    ArrayList<String> humidity, Temb, TimeState;


    private Activity context;
    ArrayList<Events>events;


    public CustList(Activity context, ArrayList<String> kind,ArrayList<Events>events,ArrayList<String>Temb,ArrayList<String>humidity) {
        super(context, R.layout.templete_item, kind);
        this.context = context;
        this.events = events;
        this.kind = kind;
        this.Temb= Temb;
        this.humidity = humidity;



        //   removeAdmin();


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();


        View listViewItem = inflater.inflate(R.layout.templete_item, null, true);
        TextView date = (TextView) listViewItem.findViewById(R.id.date);
        TextView details = (TextView) listViewItem.findViewById(R.id.details);
        TextView state = (TextView) listViewItem.findViewById(R.id.state);
        TextView humidityy = (TextView) listViewItem.findViewById(R.id.humidity);
        TextView temp = (TextView) listViewItem.findViewById(R.id.temp);
        ImageView alert = (ImageView) listViewItem.findViewById(R.id.alert);


        date.setText(events.get(position).getdateTime());
        details.setText(events.get(position).getsummary());
        state.setText(events.get(position).getStates());

        for(int i=0;i<events.size();i++){

            if(i!=position) {
                if (events.get(position).getdateTime().equals(events.get(i).getdateTime())) {

                    alert.setVisibility(View.VISIBLE);

                }
            }

        }

        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                open_dialog(events.get(position).getCreatorEmail());

            }
        });


        if (events.get(position).getTimeState().equals("not")) {

            humidityy.setText("Not found");
            temp.setText("Not found");

        } else {

            humidityy.setText("Not found");
            temp.setText(Temb.get(Integer.parseInt(events.get(position).getTimeState())));
            humidityy.setText(humidity.get(Integer.parseInt(events.get(position).getTimeState())));

        }


        return listViewItem;
    }

    void open_dialog(String Email_Creator) {


        final Dialog dialog = new Dialog(context);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        double hi = WindowManager.LayoutParams.MATCH_PARENT / 2;
        lp.height = 500;


        dialog.setContentView(R.layout.alert_dialog);
        dialog.setTitle("");
        TextView mail =  (TextView)dialog.findViewById(R.id.mail);
        mail.setText(Email_Creator);
        Button ok = (Button) dialog.findViewById(R.id.ok);



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);


    }
}
