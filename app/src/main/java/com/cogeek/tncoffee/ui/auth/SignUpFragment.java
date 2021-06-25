package com.cogeek.tncoffee.ui.auth;

import android.app.Activity;
import android.content.Context;
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

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.User;

public class SignUpFragment extends Fragment {
    private Button btnSignUp, btnSignin;
    private EditText editUserName_signup;
    private EditText editPhoneNumber_signup;
    private EditText editEmail_signup;
    private EditText editPassword_signup;
    private TextView txtSignIn;
    private SignupFragmentListener listener;

    public interface SignupFragmentListener {
        void onSignup(User user);
        void onClickLogin();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onActivityResult();
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSignUp = view.findViewById(R.id.btn_signup);
        btnSignin = view.findViewById(R.id.btn_go_signin);

        editUserName_signup = view.findViewById(R.id.editUsername);
        editEmail_signup = view.findViewById(R.id.editEmail);
        editPassword_signup = view.findViewById(R.id.editPassword);

        //get data
        btnSignUp.setOnClickListener(v -> {
            String username = editUserName_signup.getText().toString();
            String email = editEmail_signup.getText().toString();
            String password = editPassword_signup.getText().toString();
        });

        //allready have account, signin
        btnSignin.setOnClickListener( v -> {
            listener.onClickLogin();
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragment.LoginFragmentListener) {
            listener = (SignUpFragment.SignupFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement Fragment Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
