package com.cogeek.tncoffee.ui.userinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cogeek.tncoffee.R;
import com.squareup.picasso.Picasso;

public class UserInfoFragment extends Fragment {
    private ImageView imgAvatar;
    private EditText edtLastName;
    private EditText edtFirstName;
    private EditText edtPhoneNumber;
    private EditText edtBirth;
    private RadioButton btnFemale;
    private RadioButton btnMale;
    private RadioButton btnSecreSex;
    private TextView txtUpdateInfo;


    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //fake data cái
        imgAvatar = view.findViewById(R.id.imgAvatarInfo); //hình của tui á =)))
        Picasso.get().load("https://www.facebook.com/photo/?fbid=1231540623848390&set=picfp.100009773927286").into(imgAvatar);

        edtLastName = view.findViewById(R.id.edtLastNameInfo);
        edtLastName.setText("Trương");

        edtFirstName = view.findViewById(R.id.edtLastNameInfo);
        edtFirstName.setText("Thư");

        edtPhoneNumber = view.findViewById(R.id.edtPhoneNumberInfo);
        edtPhoneNumber.setText("0906368343");

        edtBirth = view.findViewById(R.id.edtBirthInfo);
        edtBirth.setText("23/02/2000");

        btnFemale = view.findViewById(R.id.btnFemaleInfo);
        btnFemale.isChecked();

        btnMale = view.findViewById(R.id.btnMaleInfo);

        btnSecreSex = view.findViewById(R.id.btnSecretSexInfo);


    }
}
