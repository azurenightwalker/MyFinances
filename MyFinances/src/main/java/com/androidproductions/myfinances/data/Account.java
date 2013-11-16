package com.androidproductions.myfinances.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public final class Account {
    public long getId() {
        return Id;
    }

    private final long Id;
    private final String Name;
    private double Balance;
    private double Overdraft;

    public Account(long id, String name) {
        Id = id;
        Name = name;
    }

    public Account(Cursor cursor) {
        Id = cursor.getLong(cursor.getColumnIndex(AccountContract._ID));
        Name = cursor.getString(cursor.getColumnIndex(AccountContract.Name));
        Balance = cursor.getInt(cursor.getColumnIndex(AccountContract.Balance))/100.0;
        Overdraft = cursor.getInt(cursor.getColumnIndex(AccountContract.Overdraft))/100.0;
    }

    public Account(Context context, Uri location) {
        Cursor cursor = context.getContentResolver().query(location,null,null,null,null);

        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                Id = cursor.getLong(cursor.getColumnIndex(AccountContract._ID));
                Name = cursor.getString(cursor.getColumnIndex(AccountContract.Name));
                Balance = cursor.getInt(cursor.getColumnIndex(AccountContract.Balance))/100.0;
                Overdraft = cursor.getInt(cursor.getColumnIndex(AccountContract.Overdraft))/100.0;
            }
            else
            {
                Id = 0L;
                Name = "N/A";
            }
            cursor.close();
        }
        else
        {
            Id = 0L;
            Name = "N/A";
        }
    }

    @Override
    public String toString() {
        return Name + ": £" + String.valueOf(Balance) +
                " (£" + String.format("%.2f", Balance + Overdraft) + " available)";
    }


}
