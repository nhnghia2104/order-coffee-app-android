package com.cogeek.tncoffee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cogeek.tncoffee.models.User;
import com.cogeek.tncoffee.ui.auth.LoginFragment;
import com.cogeek.tncoffee.ui.auth.SignUpFragment;
import com.cogeek.tncoffee.utils.SharedHelper;

public class LaunchActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener, SignUpFragment.SignupFragmentListener {
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
                .add(R.id.fragment_frame, fragment, LoginFragment.class.getSimpleName())
                .commitAllowingStateLoss();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
//        Log.d("UID", "huhuhuhu");
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_frame);
//        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSignup(User user) {

    }

    @Override
    public void onClickLogin() {
        LoginFragment fragment = new LoginFragment();
        getSupportFragmentManager().popBackStack();
//                .setCustomAnimations(R.anim.slide_in_right,
//                        R.anim.slide_out_left,
//                        R.anim.slide_in_left,
//                        R.anim.slide_out_right)
//                .replace(R.id.fragment_frame,fragment,LoginFragment.class.getSimpleName())
//                .addToBackStack(null)
//                .commit();
    }
}