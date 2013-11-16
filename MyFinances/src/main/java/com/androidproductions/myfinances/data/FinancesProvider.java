package com.androidproductions.myfinances.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class FinancesProvider extends ContentProvider
{
    private static final int ACCOUNT = 0;
    private static final int ACCOUNTS = 1;

    private static final String PROVIDER_NAME =
            "com.androidproductions.myfinances";
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(PROVIDER_NAME, "accounts", ACCOUNTS);
        uriMatcher.addURI(PROVIDER_NAME, "accounts/#", ACCOUNT);
    }

    private FinancesDB mFinancesDB;

    public boolean onCreate() {
        mFinancesDB = new FinancesDB(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Default projection if none supplied
        if(projection == null) projection = getDefaultProjection(uri);

        // Defaults & ID's
        switch(uriMatcher.match(uri))
        {
            case ACCOUNTS:
                if (TextUtils.isEmpty(sortOrder)) sortOrder = "_ID ASC";
                break;
            case ACCOUNT:
                selection = (selection == null ? "" : (selection + " and ")) +
                        AccountContract._ID + " = " + uri.getLastPathSegment();
                break;
            default:
                return null;
        }
        final SQLiteDatabase db = mFinancesDB.getReadableDatabase();
        if (db != null)
            return db.query(findTableName(uri), projection, selection, selectionArgs, null, null, sortOrder);
        return null;
    }

    public String getType(Uri uri) {
        switch(uriMatcher.match(uri))
        {
            case ACCOUNT:
                return "vnd.android.cursor.dir/vnd.com.androidproductions.myfinances.account";
            case ACCOUNTS:
                return "vnd.android.cursor.item/vnd.com.androidproductions.myfinances.account";
            default:
                return null;
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mFinancesDB.getWritableDatabase();
        if (db != null) {
            long id = db.insert(findTableName(uri),"",contentValues);
            db.close();
            return ContentUris.withAppendedId(uri, id);
        }
        return null;
    }
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mFinancesDB.getWritableDatabase();
        if (db != null) {
            int affectedRows = db.delete(findTableName(uri),selection,selectionArgs);
            db.close();
            return affectedRows;
        }
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mFinancesDB.getWritableDatabase();
        switch(uriMatcher.match(uri))
        {
            case ACCOUNT:
                selection = (selection == null ? "" : (selection + " and ")) +
                        AccountContract._ID + " = " + uri.getLastPathSegment();
                break;
            default:
                break;
        }
        if (db != null) {
            int affectedRows = db.update(findTableName(uri),contentValues,selection,selectionArgs);
            db.close();
            return affectedRows;
        }
        return 0;
    }

    private String findTableName(Uri uri)
    {
        switch(uriMatcher.match(uri))
        {
            case ACCOUNT:
                return FinancesDB.TABLE_ACCOUNTS;
            case ACCOUNTS:
                return FinancesDB.TABLE_ACCOUNTS;
            default:
                return null;
        }
    }

    private String[] getDefaultProjection(Uri uri)
    {
        switch(uriMatcher.match(uri))
        {
            case ACCOUNT:
                return AccountContract.PROJECTION;
            case ACCOUNTS:
                return AccountContract.PROJECTION;
            default:
                return null;
        }
    }
}