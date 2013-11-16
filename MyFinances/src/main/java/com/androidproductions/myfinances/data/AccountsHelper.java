package com.androidproductions.myfinances.data;

import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;

public final class AccountsHelper {
    private AccountsHelper() {
    }

    public static Account getCurrentAccount(Context context)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        long mCurrentAccount = prefs.getLong("current-account", 0L);
        if (mCurrentAccount == 0L)
            return null;
        Cursor cur = context.getContentResolver().query(ContentUris.withAppendedId(AccountContract.CONTENT_URI, mCurrentAccount),null,null,null,null);
        Account account = null;
        if (cur != null)
        {
            if (cur.moveToFirst())
            {
                account = new Account(cur);
            }
            cur.close();
        }
        return account;
    }
}
