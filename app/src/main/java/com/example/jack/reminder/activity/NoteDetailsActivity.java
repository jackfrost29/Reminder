package com.example.jack.reminder.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jack.reminder.R;
import com.example.jack.reminder.data.DataHandler;
import com.example.jack.reminder.data.Note;

public class NoteDetailsActivity extends Activity {

    int position;
    EditText title, text;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        String s = getIntent().getStringExtra("position");

        if(getIntent().hasExtra("position"))
            position = Integer.parseInt(getIntent().getStringExtra("position"));
        else
            position = -1;

        setWidgets();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(position == -1) // no note item created to be rendered in the activity
            note = new Note("", "");

        else
            note = (Note) DataHandler.getItem(position);

        title.setText(note.getTitle());
        text.setText(note.getText());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_save){
            // save the item
            String x = title.getText().toString();

            if(x.equals("")){
                // title not is set
                // you must set title for saving the item
                Toast toast = Toast.makeText(this, "You must enter a title", Toast.LENGTH_LONG);
                toast.show();

                // not finished
            }
            else{
                // title is set and item is ready to save
                note.setTitle(title.getText().toString());
                note.setText(text.getText().toString());
                if(position == -1)
                    DataHandler.setNewItem(note);

                else
                    DataHandler.setItem(note, position);

                //  item is saved and the activity will be killed
                finish();
            }
        }

        else {
            // delete the item
            if(position != -1)
                DataHandler.deleteItem(position);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void setWidgets(){
        title = (EditText)findViewById(R.id.note_title_id);
        text = (EditText)findViewById(R.id.note_text_id);

    }
}
