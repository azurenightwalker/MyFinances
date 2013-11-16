package com.androidproductions.myfinances.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FinancesDB extends SQLiteOpenHelper {
    public static final String TABLE_ACCOUNTS = "accounts";
    private static final String DATABASE_NAME = "Finances";

    private static final String DATABASE_CREATE_ACCOUNTS =
            "create table "+ TABLE_ACCOUNTS +" (" +
                    AccountContract._ID + " integer primary key autoincrement, " +
                    AccountContract.Name + " text not null, " +
                    AccountContract.Balance + " int not null, " +
                    AccountContract.Overdraft + " int null)";

    public FinancesDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_ACCOUNTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
