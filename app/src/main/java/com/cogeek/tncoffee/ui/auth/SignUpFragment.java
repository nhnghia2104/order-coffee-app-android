package com.cogeek.tncoffee.ui.auth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.api.UserApi;
import com.cogeek.tncoffee.models.ErrorResponse;
import com.cogeek.tncoffee.models.User;
import com.cogeek.tncoffee.utils.NetworkProvider;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {
    private Button btnSignUp, btnSignin;
    private EditText editUserName_signup;
    private EditText editPhoneNumber_signup;
    private EditText editEmail_signup;
    private EditText editPassword_signup;
    private TextView txtSignIn;
    private SignupFragmentListener listener;
    ProgressDialog loading = null;

    public interface SignupFragmentListener {
        void onSignupComplete();
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
        loading = new ProgressDialog(getContext());
        loading.setCancelable(true);
        loading.setMessage("Đang xác thực");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        editUserName_signup = view.findViewById(R.id.editUsername);
        editEmail_signup = view.findViewById(R.id.editEmail);
        editPassword_signup = view.findViewById(R.id.editPassword);

        //get data
        btnSignUp.setOnClickListener(v -> {
            String username = editUserName_signup.getText().toString();
            String email = editEmail_signup.getText().toString();
            String password = editPassword_signup.getText().toString();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                new AlertDialog.Builder(getContext()).setMessage("Vui lòng nhập đầy đủ thông tin").show();
            }
            else {
                UserApi userApi = NetworkProvider.self().retrofit.create(UserApi.class);
                Call<User> call = userApi.register(username,password, email);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            new AlertDialog.Builder(getContext()).setMessage("Đăng ký thành công").setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    listener.onSignupComplete();
                                }
                            }).show();
                        }
                        else {
                            loading.dismiss();
                            Gson gson = new Gson();
                            ErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(),ErrorResponse.class);
                            new AlertDialog.Builder(getContext()).setTitle("Lỗi").setMessage(errorResponse.getMessage()).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("toang",t.getMessage());
                        loading.dismiss();
                        new AlertDialog.Builder(getContext()).setTitle("Lỗi").setMessage("Đăng nhập thất bại\n vui lòng kiểm tra lại");
                    }
                });
            }
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
