package com.cogeek.tncoffee.ui.userinfo;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class UserInfoFragment extends Fragment {
    private ImageView imgAvatar;
    private EditText edtName, edtPhoneNumber, edtBirth;
    private RadioButton btnFemale;
    private RadioButton btnMale;
    private RadioButton btnSecreSex;
    private TextView txtUpdateInfo;
    private User user;

    DatabaseReference reference;

    String _NAME, _PHONE, _BIRTH;


    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_edit_info, container, false);

        reference = FirebaseDatabase.getInstance().getReference("user");
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //fake data cái
        imgAvatar = view.findViewById(R.id.imgAvatarInfo); //hình của tui á =)))
        Picasso.get().load("https://www.facebook.com/photo/?fbid=1231540623848390&set=picfp.100009773927286").into(imgAvatar);

        edtName = view.findViewById(R.id.edtNameInfo);
        //edtFirstName.setText("Thư");

        edtPhoneNumber = view.findViewById(R.id.edtPhoneNumberInfo);
        //edtPhoneNumber.setText("0906368343");

        edtBirth = view.findViewById(R.id.edtBirthInfo);
        //edtBirth.setText("23/02/2000");

        btnFemale = view.findViewById(R.id.btnFemaleInfo);
        btnFemale.isChecked();

        btnMale = view.findViewById(R.id.btnMaleInfo);

        btnSecreSex = view.findViewById(R.id.btnSecretSexInfo);

        showAllUserData();

        txtUpdateInfo = view.findViewById(R.id.txtUpdateInfo);

        txtUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateInfoUser(view);
            }
        });
    }

    private void showAllUserData() {
        Intent intent = ((Activity) getContext()).getIntent();
        _NAME = intent.getStringExtra("name");
        _PHONE = intent.getStringExtra("phonenb");
        _BIRTH = intent.getStringExtra("birthday");

        edtName.setText(_NAME);
        edtPhoneNumber.setText(_PHONE);
        edtBirth.setText(_BIRTH);
    }


    private void updateInfoUser(View view) {
        if (isNameChanged() || isPhoneChanged() || isBirthChanged()){
            //Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isBirthChanged() {
        if (!_BIRTH.equals(edtBirth.getText().toString())){
            reference.child(_BIRTH).child("birthday").setValue("");
            return true;
        } else {
            return false;
        }
    }

    private boolean isPhoneChanged() {
        if (!_PHONE.equals(edtPhoneNumber.getText().toString())){
            reference.child(_PHONE).child("phonenb").setValue("");
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameChanged() {
        if (!_NAME.equals(edtName.getText().toString())){
            reference.child(_NAME).child("name").setValue("");
            return true;
        } else {
            return false;
        }
    }
}
