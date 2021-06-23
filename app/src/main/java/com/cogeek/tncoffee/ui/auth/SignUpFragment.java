package com.cogeek.tncoffee.ui.auth;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.cogeek.tncoffee.R;

public class SignUpFragment extends Fragment {
    private Button btnSignUp;
    private EditText editUserName_signup;
    private EditText editPhoneNumber_signup;
    private EditText editEmail_signup;
    private EditText editPassword_signup;
    //private TextView txtSignIn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onActivityResult();
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSignUp = view.findViewById(R.id.btnSignUp);
        editUserName_signup = view.findViewById(R.id.editUserName_signup);
        editPhoneNumber_signup = view.findViewById(R.id.editPhoneNumber_signup);
        editEmail_signup = view.findViewById(R.id.editEmail_signup);
        editPassword_signup = view.findViewById(R.id.editPassword_signup);
        //txtSignIn = view.findViewById(R.id.txtSignIn);

        //get data
        btnSignUp.setOnClickListener(v -> {
            String username = editUserName_signup.getText().toString();
            String phonenumber = editPhoneNumber_signup.getText().toString();
            String email = editEmail_signup.getText().toString();
            String password = editPassword_signup.getText().toString();
        });

        //allready have account, signin
        view.findViewById(R.id.layoutSignIn).setOnClickListener(v -> {
            NavHostFragment.findNavController(SignUpFragment.this).navigate(R.id.signup_to_signin_fragment);
        });

    }

}
