package com.cogeek.tncoffee.ui.auth;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.api.UserApi;
import com.cogeek.tncoffee.models.User;
import com.cogeek.tncoffee.utils.NetworkProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotFragment extends Fragment {

    private ForgotFragmentListener listener;
    private TextView editEmail;
    ProgressDialog loading = null;
    public interface ForgotFragmentListener {
        void onSentEmail();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onActivityResult();
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editEmail = view.findViewById(R.id.editEmail);
        loading = new ProgressDialog(getContext());
        loading.setCancelable(true);
        loading.setMessage("Đang gửi");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        view.findViewById(R.id.btn_send_password).setOnClickListener(v -> {
            String email = editEmail.getText().toString();
            loading.show();
            sendEmail(email);
        });
    }

    private void sendEmail(String email) {
        UserApi userApi = NetworkProvider.self().retrofit.create(UserApi.class);
        Call<User> call = userApi.forgotPassword(email);



        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loading.dismiss();
                new AlertDialog.Builder(getContext()).setTitle("Xong !").setMessage("Đã gửi, kiểm tra mail đi nhé").setPositiveButton("Mở gmail", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                            getActivity().startActivity(intent);
                        } catch (android.content.ActivityNotFoundException e) {
                            Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loading.dismiss();
                new AlertDialog.Builder(getContext()).setTitle("Lỗi").setMessage("Lỗi rồi ạ");
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ForgotFragment.ForgotFragmentListener) {
            listener = (ForgotFragment.ForgotFragmentListener) context;
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
