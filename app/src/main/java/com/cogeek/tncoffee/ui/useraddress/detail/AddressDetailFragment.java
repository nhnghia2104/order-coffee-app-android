package com.cogeek.tncoffee.ui.useraddress.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Address;
import com.cogeek.tncoffee.models.User;
import com.cogeek.tncoffee.models.UserAddress;
import com.cogeek.tncoffee.ui.menu.ItemViewModel;
import com.cogeek.tncoffee.ui.menu.item.ChildItemAdapter;
import com.cogeek.tncoffee.ui.useraddress.AddressViewModel;
import com.cogeek.tncoffee.ui.userreview.UserReviewProductFragment;
import com.cogeek.tncoffee.utils.SharedHelper;

public class AddressDetailFragment extends Fragment {

    private EditText txtName, txtPhone,txtAddress, txtCity;
    private CheckBox isDefault;
    private Address target;
    private Callback callback;
    private Button btnSave;
    private AddressViewModel addressViewModel;
    private boolean createNew = false;
    private int index;

    public interface Callback {
        void onChange(Address address);
    }

    public void setOnChildListener(Callback callback) {
        this.callback = callback;
    }

    public AddressDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addressViewModel = new ViewModelProvider(requireActivity()).get(AddressViewModel.class);
        addressViewModel.getIndex().observe(getActivity(), index -> {
            if (index == -1) {
                createNew = true;
            }
            else {
                this.index = index;
                target = SharedHelper.getInstance(getContext()).getUserProfile().getUserAddress().getAddressList().get(index);
            }
        });
        return inflater.inflate(R.layout.fragment_address_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtName = view.findViewById(R.id.edit_address_name);
        txtPhone = view.findViewById(R.id.edit_address_phone);
        txtAddress = view.findViewById(R.id.edit_address_address);
        txtCity = view.findViewById(R.id.edit_address_city);
        isDefault = view.findViewById(R.id.checkBox_isDefault);
        btnSave = view.findViewById(R.id.btn_save);

        if (!createNew) {
            txtName.setText(target.getFirstName() + " " + target.getLastName());
            txtPhone.setText(target.getPhone());
            txtAddress.setText(target.getAddress());
            txtCity.setText(target.getCity());
            isDefault.setChecked(SharedHelper.getInstance(getContext()).getUserProfile().getUserAddress().getAddressDefaultIndex() == index);
        }

        view.findViewById(R.id.btn_close).setOnClickListener(v->{
            NavHostFragment.findNavController(AddressDetailFragment.this).popBackStack();
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = SharedHelper.getInstance(getContext()).getUserProfile();

                if (createNew) {
//                    chưa viết hihi
                    String name = txtName.getText().toString();
                    String city = txtCity.getText().toString();
                    String phone = txtPhone.getText().toString();
                    String address = txtAddress.getText().toString();
                    Address newAddress = new Address(name, "", city, address, phone);
                    UserAddress userAddress = user.getUserAddress();
                    userAddress.getAddressList().add(newAddress);
                    user.setUserAddress(userAddress);

                    if (isDefault.isChecked()) { // default là thg cuối
                        userAddress.setDefaultIndex(userAddress.getAddressList().size());
                    }
//                    SharedHelper.getInstance(getContext()).setUserProfile(user);
                }
                else {
                    String name = txtName.getText().toString();
                    String city = txtCity.getText().toString();
                    String phone = txtPhone.getText().toString();
                    String address = txtAddress.getText().toString();
                    UserAddress userAddress = user.getUserAddress();
                    userAddress.updateAddress(new Address(name, "", city, address, phone),index);

                    if (isDefault.isChecked()) {
                        userAddress.setDefaultIndex(index);
                    }
                }

                SharedHelper.getInstance(getContext()).setUserProfile(user);
                NavHostFragment.findNavController(AddressDetailFragment.this).popBackStack();
            }
        });
    }
}