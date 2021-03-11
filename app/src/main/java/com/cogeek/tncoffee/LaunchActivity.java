package com.cogeek.tncoffee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cogeek.tncoffee.ui.auth.LoginFragment;
import com.cogeek.tncoffee.utils.SharedHelper;

public class LaunchActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener{
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        String uid = SharedHelper.getInstance(this).getUserUidKey();
        if (uid.isEmpty()) {
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
        finish();
    }

    @Override
    public void onCompleteLoginWithGoogle(String uid) {
        Log.d("UID", uid);
        SharedHelper.getInstance(this).setUserUidKey(uid);
        onLoginCompleted();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
//        Log.d("UID", "huhuhuhu");
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_frame);
//        fragment.onActivityResult(requestCode, resultCode, data);
    }
}