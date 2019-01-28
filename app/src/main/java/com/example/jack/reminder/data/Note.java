package com.example.jack.reminder.data;

public class Note extends Item {
    String text;
    public Note(String title, String text) {
        super(title);
        this.text = text;
    }

    public Note(Note note){
        super(note.title);
        this.text = note.text;

    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }
}