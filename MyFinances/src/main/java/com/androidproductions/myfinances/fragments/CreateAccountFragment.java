package com.androidproductions.myfinances.fragments;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidproductions.myfinances.R;
import com.androidproductions.myfinances.data.Account;
import com.androidproductions.myfinances.data.AccountContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class CreateAccountFragment extends Fragment {

    public CreateAccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_account, container, false);
    }

    public void createAccount()
    {
        ContentValues cv = new ContentValues();
        cv.put(AccountContract.Name,((TextView)getView().findViewById(R.id.accountName)).getText().toString());
        cv.put(AccountContract.Balance,Double.valueOf(((TextView)getView().findViewById(R.id.accountBalance)).getText().toString())*100);
        cv.put(AccountContract.Overdraft,Double.valueOf(((TextView)getView().findViewById(R.id.accountOverdraft)).getText().toString())*100);
        Account mAccount = new Account(getActivity(), getActivity().getContentResolver().insert(AccountContract.CONTENT_URI, cv));
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
        editor.putLong("current-account", mAccount.getId());
        editor.commit();
    }
}
