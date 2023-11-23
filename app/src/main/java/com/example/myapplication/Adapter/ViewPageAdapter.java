package com.example.myapplication.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.Fragment.EditCustomer;
import com.example.myapplication.Fragment.Favorite;
import com.example.myapplication.Fragment.History;
import com.example.myapplication.Fragment.Home;
import com.example.myapplication.Fragment.Login;
import com.example.myapplication.Model.Customer;
import com.example.myapplication.SQLite.CustomerSQLite;


public class ViewPageAdapter extends FragmentStatePagerAdapter {
    private CustomerSQLite heLite;
    private Customer customer;
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        heLite = new CustomerSQLite(context);
        customer = heLite.customerWasLogin();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Home();
            case 1:
                return  new Favorite();
            case 2:
                return  new History();
            case 3:
                if(customer == null) {
                    return  new Login();
                } else {
                    return new EditCustomer();
                }


            default:
                return new Home();

        }

    }
    // tra ve so luong tap fragment
    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title ="Trang chủ";
                break;
            case 1:
                title="Giải trí";
                break;
            case 2:
                title="Lịch sử";
                break;
            case 3:
                title="Cá nhân";
                break;
        }
        return title;
    }
}

