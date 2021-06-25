package com.cogeek.tncoffee.ui.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.api.OrderApi;
import com.cogeek.tncoffee.models.Address;
import com.cogeek.tncoffee.models.Cart;
import com.cogeek.tncoffee.models.ItemCart;
import com.cogeek.tncoffee.models.Order;
import com.cogeek.tncoffee.models.TimelineOrder;
import com.cogeek.tncoffee.models.TrackingOrder;
import com.cogeek.tncoffee.models.User;
import com.cogeek.tncoffee.models.UserAddress;
import com.cogeek.tncoffee.ui.useraddress.AddressViewModel;
import com.cogeek.tncoffee.utils.NetworkProvider;
import com.cogeek.tncoffee.utils.NumberHelper;
import com.cogeek.tncoffee.utils.SharedHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;
    private List<ItemCart> cartDetails;
    private ItemCartAdapter itemCartAdapter;
    private AddressViewModel addressViewModel;
    private RecyclerView recyclerView;
    private TextView txtPriceFinal, txtAddress, txtName;
    private Button btnConfirmCart;
    private Double totalCart;
    private boolean needCreateNewAddress = true;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        addressViewModel = new ViewModelProvider(requireActivity()).get(AddressViewModel.class);
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        cartDetails = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView_cart_item);
        itemCartAdapter = new ItemCartAdapter(cartDetails);
        recyclerView.setAdapter(itemCartAdapter);
        itemCartAdapter.setOnItemListener(onItemListener);
        registerLiveDataListenner();

        txtPriceFinal = view.findViewById(R.id.txtPriceFinal);
        btnConfirmCart = view.findViewById(R.id.btnConfirmCart);
        btnConfirmCart.setOnClickListener(onConfirmCartListener);
        txtName = view.findViewById(R.id.txt_ship_name);
        txtAddress = view.findViewById(R.id.txt_ship_address);

        Cart cart = SharedHelper.getInstance(getActivity()).getCart();
        if ( cart != null) {
            cartDetails.addAll(cart.getItemList());
            itemCartAdapter.notifyDataSetChanged();
        }

        view.findViewById(R.id.btn_close).setOnClickListener(v -> {
            NavHostFragment.findNavController(CartFragment.this).navigate(R.id.action_cartFragment_to_navigation_menu);
        });
        view.findViewById(R.id.layout_address_order).setOnClickListener(v-> {
            NavHostFragment.findNavController(CartFragment.this).navigate(R.id.action_cartFragment_to_userAddressFragment);
        });

        addressViewModel.getCartSelected().observe(getActivity(), index -> {
            if (index == -1) {
                this.needCreateNewAddress = true;
            }
            else {
                this.needCreateNewAddress = false;
                User user = SharedHelper.getInstance(getContext()).getUserProfile();
                loadAddress(user.getUserAddress().getAddressList().get(index));
            }
        });

    }

    ItemCartAdapter.OnItemListener onItemListener = new ItemCartAdapter.OnItemListener() {
        @Override
        public void onItemClick(int row) {

        }

        @Override
        public void onIncrease(int row) {
            cartViewModel.addItem(cartDetails.get(row));
        }

        @Override
        public void onDecrease(int row) {
            cartViewModel.removeItem(cartDetails.get(row).getId());
        }

        @Override
        public void onRemove(int row) {
            cartViewModel.deleteItemCart(cartDetails.get(row));
        }
    };

    View.OnClickListener onConfirmCartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (cartDetails.size() > 0 && !needCreateNewAddress) {
                createOrder();
//                NavHostFragment.findNavController(CartFragment.this).navigate(R.id.action_cartFragment_to_orderFragment);
            }
        }
    };

    private void loadAddress(Address address) {
            txtName.setText(address.getFullName() + " | " + address.getPhone());
            txtAddress.setText(address.getAddress() + ", " + address.getCity());
    }

    private void createOrder() {

        // Lấy thông tin user
        User user = SharedHelper.getInstance(getActivity()).getUserProfile();
        Gson gson = new Gson();

        // Danh sách sản phẩm -> string
        String prodcutListString = gson.toJson(cartDetails);

        // Ngày tạo đơn hàng -> string
        String dateString = NumberHelper.getInstance().dateFormatForDatabase(new Date().getTime());

        //Dòng thời gian, khi nào cần dùng thì quên rùi hihi
        List<TimelineOrder> timelineOrderList = new ArrayList<>();
        timelineOrderList.add(new TimelineOrder(dateString,"1","Đặt hàng thành công"));
        String timeline = gson.toJson(timelineOrderList);

        // Theo dõi đơn hàng
        List<TrackingOrder> trackingOrders = new ArrayList<>();
        trackingOrders.add(new TrackingOrder(dateString,"1","Đặt hàng thành công"));
        String tracking = gson.toJson(trackingOrders);

        // Địa chỉ giao hàng
        Address shippingAddress = user.getUserAddress().getUserAddressDefault();
        String shippingAddressString = gson.toJson(shippingAddress);

        // Gửi thông tin lên Server
        OrderApi orderApi = NetworkProvider.self().retrofit.create(OrderApi.class);
        Call<Order> call = orderApi.create(new Date().getTime(),
                dateString,
                totalCart,
                1,
                1,
                0,
                "0",
                "",
                user.getUid(),
                timeline,
                prodcutListString,
                tracking,
                shippingAddressString,
                1);

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.i("response", response.message());
                if (response.isSuccessful()) {
                    Log.i("non", "ngon");
                    cartViewModel.clearCart();
                }
            }
            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("errror", t.getMessage());
            }
        });
    }

    public void registerLiveDataListenner() {
        cartViewModel.getCart().observe(getViewLifecycleOwner(), cart -> {
            cartDetails.clear();
            cartDetails.addAll(cart.getItemList());
            itemCartAdapter.notifyDataSetChanged();
            txtPriceFinal.setText(cart.totalCartPriceToString());
            totalCart = cart.totalCartPrice();
        });
    }
}