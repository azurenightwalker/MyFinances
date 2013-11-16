package com.androidproductions.myfinances.data;

import android.net.Uri;

public class TransactionContract {

    public static final String _ID = "_id";
    public static final String Date = "date";
    public static final String TimeIn = "timeIn";
    public static final String TimeOut = "timeOut";
    public static final String Projects = "projects";

    public static final Uri CONTENT_URI =
            Uri.parse("content://uk.co.xlabsystems.timetracker/days");

    public static final String[] PROJECTION = new String[] {
            TransactionContract._ID,
            TransactionContract.Date,
            TransactionContract.TimeIn,
            TransactionContract.TimeOut,
            TransactionContract.Projects
    };
}
