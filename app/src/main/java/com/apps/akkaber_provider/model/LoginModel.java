package com.apps.akkaber_provider.model;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.apps.akkaber_provider.BR;
import com.apps.akkaber_provider.R;

import java.io.Serializable;

public class LoginModel extends BaseObservable implements Serializable {
    private String username;
    private String password;
    public ObservableField<String> error_user_name = new ObservableField<>();
    public ObservableField<String> error_password = new ObservableField<>();

    public LoginModel() {
        username = "";
        password = "";
    }

    public boolean isDataValid(Context context) {
        if (!username.isEmpty() &&
                password.length() >= 6) {
            error_user_name.set(null);
            error_password.set(null);


            return true;
        } else {

            Log.e("dsds","dd");
            if (username.isEmpty()) {
                error_user_name.set(context.getString(R.string.field_required));

            } else {
                error_user_name.set(null);

            }

            if (password.isEmpty()) {
                error_password.set(context.getString(R.string.field_required));

            } else if (password.length() < 6) {
                error_password.set(context.getString(R.string.pass_short));

            } else {
                error_password.set(null);

            }


            return false;
        }
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);

    }


}