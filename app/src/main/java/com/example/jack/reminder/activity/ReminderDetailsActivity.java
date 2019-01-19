package com.example.jack.reminder.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jack.reminder.data.DataHandler;
import com.example.jack.reminder.R;
import com.example.jack.reminder.data.MyTime;
import com.example.jack.reminder.data.Reminder;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ReminderDetailsActivity extends Activity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    int position;
    EditText title;
    Button selectDate, selectTime;
    Reminder reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_details);
        String s = getIntent().getStringExtra("position");
        position = (s.equals(null)) ? -1 : Integer.parseInt(s);

        setWidgets();

    }

    @Override
    protected void onStart() {
        super.onStart();
        // setting texts of
        // title, date, time etc;
        if(position == -1){
            reminder = new Reminder("", new MyTime());
            // New Reminder Item
            // no need to set anything
        }
        else{
            reminder = (Reminder) DataHandler.getItem(position);

        }

        title.setText(reminder.getTitle());  // title set
        selectDate.setText(reminder.getRemTime().getDayString());    // date set
        selectTime.setText(reminder.getRemTime().getTimeString());   // time set

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete){
            // delete the reminder object from dataset and kill the activity

            if(position != -1)  //delete when position is not -1; i.e when the item to be deleted exists in the list
                DataHandler.deleteItem(position);
        }

        else{
            // save the item to dataset and kill the activity
            String toastString = "You Must Select ";
            String x = title.getText().toString();
            int y = reminder.getRemTime().isFlagSet();
            ArrayList<String> notSet = new ArrayList<>();
            if(x.equals(""))    notSet.add("Title");
            if(y == 1)  notSet.add("Time");
            if(y==2)    notSet.add("Data");
            if(y == 3){
                notSet.add("Time");
                notSet.add("Date");
            }
            if(notSet.isEmpty() == true)
                toastString = "";
            else {
                for(int i=0; i<notSet.size(); i++){
                    toastString = toastString+notSet.get(i);
                    if(i != notSet.size()-1)
                        toastString = toastString + ", ";

                }
            }
            if(toastString.equals("") == false){
                Toast toast = Toast.makeText(this, toastString, Toast.LENGTH_LONG);
                toast.show();
            }
            else{
                // everything is ok, now you may kill the activity and save the data

                reminder.setTitle(title.getText().toString());
                if(position == -1)
                    DataHandler.setNewItem(reminder);
                else
                    DataHandler.setItem(reminder, position);

                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        /* if time or date is selected for the first time
            then there was no previous reminder object created for rendering this particular activity
            otherwise the the reminder object already created will serve the purpose
        */
        GregorianCalendar date = new GregorianCalendar(year, month, dayOfMonth-1);
        int dayOfWeek=date.get(date.DAY_OF_WEEK);

        reminder.getRemTime().setDateManually(dayOfMonth, dayOfWeek, month, year);
        selectDate.setText(reminder.getRemTime().getDayString());   // set the newly selected day to button

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) { // when time button is selected
        int hour = hourOfDay % 12;
        if(hour == 0)
            hour = 12;
        String amPm_ = (hourOfDay>=12) ? "PM" : "AM";
        reminder.getRemTime().setTimeManually(hour, minute, amPm_);
        selectTime.setText(reminder.getRemTime().getTimeString());  // set the newly selected day to button

    }

    public void setWidgets(){
        title = (EditText)findViewById(R.id.title_of_the_reminder);
        selectDate = (Button)findViewById(R.id.select_date_of_reminder);
        selectTime = (Button)findViewById(R.id.select_time_of_reminder);

        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // incomplete
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // incomplete
            }
        });

    }

}