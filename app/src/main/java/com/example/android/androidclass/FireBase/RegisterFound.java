package com.example.android.androidclass.FireBase;

import java.util.Date;

/**
 * Created by josep on 3/1/2017.
 */


public class RegisterFound {
    public String found_name;
    public String found_contact;
    public String found_desc;
    public String found_where;
    public String found_stat;
    public String found_statusFF;
    public String user_ID;
    public String found_nameStatDescWhere;
    private long messageTime;

    public RegisterFound() {
    }


    public RegisterFound(String foundName, String foundContact, String foundDesc, String foundWhere, String userID, String foundStat,String foundStatusFF, String foundNameStatDescWhere) {
        this.found_name = foundName;
        this.found_contact = foundContact;
        this.found_desc = foundDesc;
        this.found_where = foundWhere;
        this.found_stat = foundStat;
        this.found_statusFF =foundStatusFF;
        this.user_ID = userID;
        this.found_nameStatDescWhere = foundNameStatDescWhere;

        // Initialize to current time
        messageTime = new Date().getTime();
    }



    public String getFound_name() {
        return found_name;
    }

    public void setFound_name(String found_name) {
        this.found_name = found_name;
    }

    public String getFound_contact() {
        return found_contact;
    }

    public void setFound_contact(String found_contact) {
        this.found_contact = found_contact;
    }

    public String getFound_desc() {
        return found_desc;
    }

    public String getFound_where() {
        return found_where;
    }

    public void setFound_where(String found_where) {
        this.found_where = found_where;
    }

    public void setFound_desc(String found_desc) {
        this.found_desc = found_desc;
    }

    public String getFound_stat() { return found_stat;}

    public void setFound_stat(String found_stat) { this.found_stat = found_stat;}

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }


    public String getFound_statusFF() { return found_statusFF; }

    public void setFound_statusFF(String found_statusFF) { this.found_statusFF = found_statusFF; }


    public String getFound_nameStatDescWhere() { return found_nameStatDescWhere; }

    public void setFound_nameStatDescWhere(String found_nameStatDescWhere) { this.found_nameStatDescWhere = found_nameStatDescWhere; }


}

