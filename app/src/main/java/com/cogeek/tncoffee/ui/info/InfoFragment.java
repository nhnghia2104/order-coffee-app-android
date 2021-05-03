package com.cogeek.tncoffee.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.utils.SharedHelper;

public class InfoFragment {
    private ImageView imgAvatar;
    private TextView txtLastName;
    private TextView txtFirstName;
    //private TextView

    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_edit_info, container, false);
    }
}
