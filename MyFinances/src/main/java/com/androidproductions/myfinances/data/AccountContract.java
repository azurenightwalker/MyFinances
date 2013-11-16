package com.androidproductions.myfinances.data;

import android.net.Uri;

public class AccountContract {

    public static final String _ID = "_id";
    public static final String Name = "name";
    public static final String Balance = "balance";
    public static final String Overdraft = "overdraft";

    public static final Uri CONTENT_URI =
            Uri.parse("content://com.androidproductions.myfinances/accounts");

    public static final String[] PROJECTION = new String[] {
            AccountContract._ID,
            AccountContract.Name,
            AccountContract.Balance,
            AccountContract.Overdraft
    };
}
