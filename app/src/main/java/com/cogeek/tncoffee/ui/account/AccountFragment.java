package com.cogeek.tncoffee.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cogeek.tncoffee.LaunchActivity;
import com.cogeek.tncoffee.MainActivity;
import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.utils.SharedHelper;

public class AccountFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnLogout).setOnClickListener(v -> {
            logout();
        });

    }
    private void logout() {
        SharedHelper.getInstance(getActivity()).logout();
        Intent intent = new Intent(getActivity(), LaunchActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(intent);
        getActivity().finish();
    }
}
