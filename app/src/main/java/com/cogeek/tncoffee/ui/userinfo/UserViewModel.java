package com.cogeek.tncoffee.ui.userinfo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cogeek.tncoffee.models.Cart;
import com.cogeek.tncoffee.models.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private MutableLiveData<User> mUser = new MutableLiveData<>();
    private User user;

    public UserViewModel(@NonNull Application application) {
        super(application);
        user = new User();
        mUser.setValue(user);
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }

}
