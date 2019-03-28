package com.example.mgebhart16woche23;

import java.util.Date;

public class Note {

    private String ID;
    private boolean checked;
    private boolean dringend;
    private String dateTime;
    private String note;

    public Note() {
    }

    public Note(String ID, boolean checked, boolean dringend, String dateTime, String note) {
        this.ID = ID;
        this.checked = checked;
        this.dringend = dringend;
        this.dateTime = dateTime;
        this.note = note;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDringend() {
        return dringend;
    }

    public void setDringend(boolean dringend) {
        this.dringend = dringend;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
