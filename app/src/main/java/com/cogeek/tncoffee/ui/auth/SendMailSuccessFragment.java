package com.cogeek.tncoffee.ui.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cogeek.tncoffee.R;
public class SendMailSuccessFragment extends Fragment {

    private SendMailSuccessFragmentListener listener;
    private TextView editEmail;
    public interface SendMailSuccessFragmentListener {
        void onClickLogin();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            listener.onClickLogin();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_mail_success, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SendMailSuccessFragment.SendMailSuccessFragmentListener) {
            listener = (SendMailSuccessFragment.SendMailSuccessFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement Fragment Listener");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listener.onClickLogin();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}