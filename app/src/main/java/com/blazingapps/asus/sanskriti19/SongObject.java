package com.blazingapps.asus.sanskriti19;

import com.google.firebase.firestore.FieldValue;

public class SongObject {

    String name = "";
    String movie = "";
    String dedicated = "";
    boolean played = false;
    String authname = "";
    FieldValue time = FieldValue.serverTimestamp();
    public SongObject(String name, String movie, String dedicated, String authname) {
        this.name = name;
        this.movie = movie;
        this.authname = authname;
        this.dedicated = dedicated;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getDedicated() {
        return dedicated;
    }

    public void setDedicated(String dedicated) {
        this.dedicated = dedicated;
    }

    public String getAuthname() {
        return authname;
    }

    public void setAuthname(String authname) {
        this.authname = authname;
    }
}
