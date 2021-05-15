package com.cogeek.tncoffee.ui.userinfo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class UserInfoFragment extends Fragment {
    private Context mContext;
    private UserViewModel userViewModel;
    private ImageView imgAvatar;
    private EditText edtName, edtPhoneNumber, edtBirth;
    private RadioGroup radioGroupGender;
    private TextView txtUpdateInfo;
    private User user;
    private static final int CAMERA_REQUEST_CODE = 102;
    private static final int CAMERA_CODE = 101;
    private static final int AVATAR_GALLERY_REQUEST = 202;

    DatabaseReference reference;

    String _NAME, _PHONE, _BIRTH;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        View root =  inflater.inflate(R.layout.fragment_edit_info, container, false);

        //reference = FirebaseDatabase.getInstance().getReference("user");
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //fake data cái
        imgAvatar = view.findViewById(R.id.imgAvatarInfo); //hình của tui á =)))
        //Picasso.get().load("https://www.facebook.com/photo/?fbid=1231540623848390&set=picfp.100009773927286").into(imgAvatar);
        imgAvatar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //open Camera
                String strAvatarPrompt = "Take your picture to store as your avatar!";
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, CAMERA_REQUEST_CODE);
            }
        });
        imgAvatar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //open Gallery
                String strAvatarPrompt = "Choose a picture to use as your avatar!";
                Intent pickPhoto = new Intent(Intent.ACTION_PICK);
                pickPhoto.setType("image/*");
                startActivityForResult(Intent.createChooser(pickPhoto, strAvatarPrompt), AVATAR_GALLERY_REQUEST);
                return true;
            }
        });

        edtName = view.findViewById(R.id.edtNameInfo);
        //edtFirstName.setText("Thư");

        edtPhoneNumber = view.findViewById(R.id.edtPhoneNumberInfo);
        //edtPhoneNumber.setText("0906368343");

        edtBirth = view.findViewById(R.id.edtBirthInfo);
        //edtBirth.setText("23/02/2000");

        radioGroupGender = view.findViewById(R.id.radioGroupGender);

        RadioButton btnGenderSelected = view.findViewById(radioGroupGender.getCheckedRadioButtonId());
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton btnGenderSelected = view.findViewById(checkedId);
            }
        });

        showAllUserData();

        txtUpdateInfo = view.findViewById(R.id.txtUpdateInfo);

        txtUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateInfoUser(view);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
            imgAvatar.setImageBitmap(imgBitmap);
        }
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
            Toast.makeText(mContext, "updated", Toast.LENGTH_SHORT).show();
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

    public void registerLiveDataListenner() {
        userViewModel.getCart().observe(getViewLifecycleOwner(), cart -> {

        });
    }
}
