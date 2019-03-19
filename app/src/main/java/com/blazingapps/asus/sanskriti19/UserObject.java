package com.blazingapps.asus.sanskriti19;

public class UserObject {
    String name;
    String email;
    String uid;
    String provider;
    String phoneno;
    String collagename;
    String semester;
    String profilepicurl;

    public UserObject(String name, String email, String uid, String provider, String phoneno, String collagename, String semester, String profilepicurl) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.provider = provider;
        this.phoneno = phoneno;
        this.collagename = collagename;
        this.semester = semester;
        this.profilepicurl = profilepicurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getCollagename() {
        return collagename;
    }

    public void setCollagename(String collagename) {
        this.collagename = collagename;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getProfilepicurl() {
        return profilepicurl;
    }

    public void setProfilepicurl(String profilepicurl) {
        this.profilepicurl = profilepicurl;
    }
}
