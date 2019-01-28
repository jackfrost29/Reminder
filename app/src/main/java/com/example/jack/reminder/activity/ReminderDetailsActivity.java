package com.example.jack.reminder.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jack.reminder.data.DataHandler;
import com.example.jack.reminder.R;
import com.example.jack.reminder.data.MyTime;
import com.example.jack.reminder.data.Reminder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReminderDetailsActivity extends Activity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    int position;
    EditText title;
    Button selectDate, selectTime;
    Switch alarmSwitch;
    Reminder reminder;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_details);

        if(getIntent().hasExtra("position"))
            position = getIntent().getIntExtra("position", 0);

        else
            position = -1;

        setWidgets();

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Log.d("onCreate: ", "after");

    }

    @Override
    protected void onStart() {
        super.onStart();
        // setting texts of
        // title, date, time etc;

        if(position == -1)
            reminder = new Reminder("", new MyTime());

        else {
            reminder = (Reminder) DataHandler.getItem(position);
            title.setText(reminder.getTitle());  // title set
            selectDate.setText(reminder.getRemTime().getDayString());    // date set
            selectTime.setText(reminder.getRemTime().getTimeString());   // time set
            alarmSwitch.setChecked(reminder.getIsAlarmSet());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /* when will the alarm be set?
            when the alarmSwitch will be set checked;
         */

        if(item.getItemId() == R.id.action_delete){
            // delete the reminder object from dataset and kill the activity

            if(position != -1)  //delete when position is not -1; i.e when the item to be deleted exists in the list
                DataHandler.deleteItem(position);

            finish();
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

            // to save or not to save
            if(!toastString.equals("")){
                Toast toast = Toast.makeText(this, toastString, Toast.LENGTH_LONG);
                toast.show();
            }
            else{
                // everything is ok, now you may kill the activity and save the data

                // set reminder title
                reminder.setTitle(title.getText().toString());

                //no need to set reminder date and time because it's already done

                //set or cancel alarm

                Intent intent = new Intent(this, MyBroadcastReceiver.class);

                intent.putExtra("source", "Reminder");
                if(position == -1)
                    intent.putExtra("position", DataHandler.getFullList().size());
                else
                    intent.putExtra("position", position);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


                reminder.setAlarmManager(alarmManager, pendingIntent);
                reminder.setIsAlarmSet(alarmSwitch.isChecked());

                // finally set the alarm

                reminder.setAlarm();

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

        Log.d("onDateSet: ", "1");
        GregorianCalendar date = new GregorianCalendar(year, month, dayOfMonth-1);
        int dayOfWeek=date.get(date.DAY_OF_WEEK);

        Log.d("onDateSet: ", "2");
        reminder.getRemTime().setDateManually(dayOfMonth, dayOfWeek, month, year);
        Log.d("onDateSet: ", "3");
        selectDate.setText(reminder.getRemTime().getDayString());   // set the newly selected day to button
        Log.d("onDateSet: ", "4");

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
        alarmSwitch = (Switch)findViewById(R.id.reminder_activity_switch_id);

        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour, minute;
                if(reminder.getRemTime().isTimeSet()){
                    hour = reminder.getRemTime().getHour();
                    minute = reminder.getRemTime().getMinute();
                }
                // create Time fragment
                else {
                    Calendar calendar = Calendar.getInstance();
                    hour = calendar.get(Calendar.HOUR);
                    minute = calendar.get(Calendar.MINUTE);
                }

                TimePickerDialog timePicker = new TimePickerDialog(
                        ReminderDetailsActivity.this, ReminderDetailsActivity.this,
                        hour, minute, DateFormat.is24HourFormat(ReminderDetailsActivity.this)
                );
                timePicker.show();
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day, month, year;
                if(reminder.getRemTime().isDateSet()){
                    day = reminder.getRemTime().getDayOfMonth();
                    month = reminder.getRemTime().getMonth();
                    year = reminder.getRemTime().getYear();
                }

                else {
                    Calendar c = Calendar.getInstance();
                    day = c.get(Calendar.DAY_OF_MONTH);
                    month = c.get(Calendar.MONTH);
                    year = c.get(Calendar.YEAR);

                }
                DatePickerDialog datePicker = new DatePickerDialog(
                        ReminderDetailsActivity.this, ReminderDetailsActivity.this,
                        year, month, day
                );
                datePicker.show();
            }
        });

    }

}
