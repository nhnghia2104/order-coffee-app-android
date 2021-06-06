package com.cogeek.tncoffee.ui.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.api.UserApi;
import com.cogeek.tncoffee.models.User;
import com.cogeek.tncoffee.utils.NetworkProvider;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    private Button btnLoginGoogle;
    private Button btnOk;
    private EditText editUsername;
    private EditText editPassword;
    private LoginFragmentListener listener;
    private static final int RC_SIGN_IN = 123;

    public interface LoginFragmentListener {
        void onCompleteLogin(User user);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onActivityResult();
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLoginGoogle = view.findViewById(R.id.btnLoginGoogle);
        view.findViewById(R.id.btnLoginGoogle).setOnClickListener((v) -> {
            loginWithGoogle();
        });

        btnOk = view.findViewById(R.id.btnOk);
        editUsername = view.findViewById(R.id.editUsername);
        editPassword = view.findViewById(R.id.editPassword);





        btnOk.setOnClickListener(v -> {
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();
            Log.i("username", username);
            Log.i("password", password);

            if (username != "" && password != "") {
                UserApi userApi = NetworkProvider.self().retrofit.create(UserApi.class);
                Call<User> call = userApi.authenticate(username,password);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();
                            listener.onCompleteLogin(user);
//                            SharedHelper.getInstance(getContext()).setUserProfile(user);
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });

    }

    private void loginWithGoogle() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers).setTosAndPrivacyPolicyUrls(
                        "https://example.com/terms.html",
                        "https://example.com/privacy.html")
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                listener.onCompleteLogin(user.getUid());
                Toast.makeText(getActivity(), "Hello " + user.getDisplayName(), Toast.LENGTH_LONG).show();
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragmentListener) {
            listener = (LoginFragmentListener) context;
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
