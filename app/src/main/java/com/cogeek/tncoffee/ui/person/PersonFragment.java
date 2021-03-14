package com.cogeek.tncoffee.ui.person;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.cogeek.tncoffee.R;

public class PersonFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.setting_preferences, rootKey);
    }
}