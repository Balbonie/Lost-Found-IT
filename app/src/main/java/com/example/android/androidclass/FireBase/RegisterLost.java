package com.example.android.androidclass.FireBase;

import java.util.Date;

/**
 * Created by josep on 3/1/2017.
 */


public class RegisterLost {


    public String lost_name;
    public String lost_contact;
    public String lost_desc;
    public String lost_where;
    public String user_ID;
    public String lost_stat;
    public String lost_statusFL;
    public String lost_nameStatDescWhere;
    public long messageTime;

    public RegisterLost() {
    }


    public RegisterLost(String lostName, String lostContact, String lostDesc, String lostWhere, String userID, String lostStat, String lostStatusFL, String lostNameStatDescWhere) {
        this.lost_name = lostName;
        this.lost_contact = lostContact;
        this.lost_desc = lostDesc;
        this.lost_where = lostWhere;
        this.user_ID = userID;
        this.lost_stat = lostStat;
        this.lost_statusFL = lostStatusFL;
        this.lost_nameStatDescWhere = lostNameStatDescWhere;

        // Initialize to current time
        messageTime = new Date().getTime();
    }


    public String getLost_name() {
        return lost_name;
    }

    public void setLost_name(String lost_name) {
        this.lost_name = lost_name;
    }

    public String getLost_contact() {
        return lost_contact;
    }

    public void setLost_contact(String lost_contact) {
        this.lost_contact = lost_contact;
    }

    public String getLost_desc() {
        return lost_desc;
    }

    public void setLost_desc(String lost_desc) {
        this.lost_desc = lost_desc;
    }

    public String getLost_where() {
        return lost_where;
    }

    public void setLost_where(String lost_where) {
        this.lost_where = lost_where;
    }

    public String getLost_stat() { return lost_stat;}

    public void setLost_stat(String lost_stat) { this.lost_stat = lost_stat;}

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }


    public String getLost_statusFL() { return lost_statusFL; }

    public void setLost_statusFL(String lost_statusF) { this.lost_statusFL = lost_statusF; }


    public String getLost_nameStatDescWhere() { return lost_nameStatDescWhere; }

    public void setLost_nameStatDescWhere(String lost_nameStatDescWhere) { this.lost_nameStatDescWhere = lost_nameStatDescWhere; }



}

