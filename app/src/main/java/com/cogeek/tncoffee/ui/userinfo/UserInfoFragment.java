package com.cogeek.tncoffee.ui.userinfo;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cogeek.tncoffee.R;

public class UserInfoFragment extends Fragment {
    private ImageView imgAvatar;
    private EditText edtLastName;
    private EditText edtFirstName;
    private EditText edtPhoneNumber;
    private EditText edtBirth;
    private CheckBox btnFemale;
    private CheckBox btnMale;
    private CheckBox btnSecreSex;


    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_edit_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //fake data cái
        imgAvatar = view.findViewById(R.id.imgAvatarInfo);

        edtLastName = view.findViewById(R.id.edtLastNameInfo);

        edtFirstName = view.findViewById(R.id.edtLastNameInfo);

        edtPhoneNumber = view.findViewById(R.id.edtPhoneNumberInfo);

        edtBirth = view.findViewById(R.id.edtBirthInfo);

        btnFemale = view.findViewById(R.id.btnFemaleInfo);

        btnMale = view.findViewById(R.id.btnMaleInfo);

        btnSecreSex = view.findViewById(R.id.btnSecretSexInfo);
        


    }
}
