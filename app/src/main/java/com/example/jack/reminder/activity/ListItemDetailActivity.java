package com.example.jack.reminder.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jack.reminder.data.DataHandler;
import com.example.jack.reminder.data.ListItem;
import com.example.jack.reminder.R;
import com.example.jack.reminder.data.MyTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ListItemDetailActivity extends Activity implements DatePickerDialog.OnDateSetListener {

    private LinearLayout parentLinearLayout;
    private Integer position;
    Button addButton, dateButton;
    ListItem listItem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_details, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_save){
            // save newly created item to item list
            int count = parentLinearLayout.getChildCount();
            ArrayList<String> createdList = new ArrayList<>();

            for(int i=0; i<count-1; i++){   // runs count-1 times as count'th time is the button
                View view = parentLinearLayout.getChildAt(i);
                EditText x = (EditText)view.findViewById(R.id.number_edit_text);
                createdList.add(x.getText().toString());
            }
            listItem.setList(createdList);
//            DataHandler.setNewItem(new ListItem("<to-do-list>", createdList));

            String toastString = "You Must Select ";
            ArrayList<String> notSet = new ArrayList<>();
            if(createdList.equals(""))    notSet.add("Title");
            if(listItem.getMyTime().isDateSet() == false)    notSet.add("Data");

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
            if(toastString.equals("") == false){    // there are item left to be saved
                Toast toast = Toast.makeText(this, toastString, Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                if(position == -1)
                    DataHandler.setNewItem(listItem);
                else
                    DataHandler.setItem(listItem, position);
                // finish the activity
                finish();
            }
        }

        else {  // delete the item

            if(position != -1)
                DataHandler.deleteItem(position);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_detail);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        if(getIntent().hasExtra("position"))
            position = getIntent().getIntExtra("position", 0);

        else
            position = -1;


        setWidgets();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (position == -1)
            listItem = new ListItem();
        else {
            listItem = (ListItem) DataHandler.getItem(position);
            loadFields((ArrayList<String>) listItem.getList());
        }


    }

    public void loadFields(ArrayList<String> list){
/*        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);*/
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(int i=0; i<list.size(); i++){

            // alhamdulillah

            View rowView = inflater.inflate(R.layout.field, null);
            ((EditText)rowView.findViewById(R.id.number_edit_text)).setText(list.get(i));
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        }

    }

    public void onAddField(View v) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.


        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);

    }

    public void setWidgets(){
        addButton = (Button)findViewById(R.id.add_field_button);
        dateButton = (Button)findViewById(R.id.add_date_button);
    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        GregorianCalendar date = new GregorianCalendar(year, month, dayOfMonth-1);
        int dayOfWeek = date.get(date.DAY_OF_WEEK);
        listItem.getMyTime().setDateManually(dayOfMonth, dayOfWeek, month, year);
        dateButton.setText(listItem.getMyTime().getDayString());
    }

    public void onSelectDate(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dp = new DatePickerDialog(this, this, year, month, day);
        dp.show();
    }
}
