package com.cogeek.tncoffee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cogeek.tncoffee.models.User;
import com.cogeek.tncoffee.ui.auth.ForgotFragment;
import com.cogeek.tncoffee.ui.auth.LoginFragment;
import com.cogeek.tncoffee.ui.auth.SendMailSuccessFragment;
import com.cogeek.tncoffee.ui.auth.SignUpFragment;
import com.cogeek.tncoffee.utils.SharedHelper;

public class LaunchActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener,
        SignUpFragment.SignupFragmentListener,
        ForgotFragment.ForgotFragmentListener,
        SendMailSuccessFragment.SendMailSuccessFragmentListener {
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        String uid = SharedHelper.getInstance(this).getUserUidKey();
        User user = SharedHelper.getInstance(this).getUserProfile();
        if (user == null) {
            addLoginFragment();
        } else {
            onLoginCompleted();
        }
    }

    private void addLoginFragment() {
        LoginFragment fragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out)
                .add(R.id.fragment_frame, fragment, LoginFragment.class.getSimpleName())
                .commitAllowingStateLoss();
    }

    private void addSignupFragment() {
        SignUpFragment fragment = new SignUpFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .replace(R.id.fragment_frame,fragment,SignUpFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    private void addForgotPasswordFragment() {
        ForgotFragment fragment = new ForgotFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .replace(R.id.fragment_frame,fragment,ForgotFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    private void addSuccessFragment() {
        SendMailSuccessFragment fragment = new SendMailSuccessFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .replace(R.id.fragment_frame,fragment,SendMailSuccessFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    private void onLoginCompleted() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    public void onCompleteLogin(User user) {
        SharedHelper.getInstance(this).setUserProfile(user);
        onLoginCompleted();
    }

    @Override
    public void onClickSignup() {
        SignUpFragment fragment = new SignUpFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .replace(R.id.fragment_frame,fragment,SignUpFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClickForgotPassword() {
        addForgotPasswordFragment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
//        Log.d("UID", "huhuhuhu");
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_frame);
//        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSignupComplete() {
        addLoginFragment();
    }

    @Override
    public void onClickLogin() {
        LoginFragment fragment = new LoginFragment();
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onSentEmail() {
//        addSuccessFragment();
    }
}