package com.example.android.myapplication;

import android.widget.TextView;

public class Notification {
    private String location;
    private String message;

    public Notification(String location, String message) {
        this.location = location;
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public String getMessage() {
        return message;
    }
}
