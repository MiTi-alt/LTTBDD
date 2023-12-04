package com.example.myapplication.Customer.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.Customer.Activity.MainActivity;
import com.example.myapplication.Model.Customer;
import com.example.myapplication.R;
import com.example.myapplication.SQLite.CustomerSQLite;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditCustomer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditCustomer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnLogOut;
    private CustomerSQLite helper;
    private Customer customer;

    public EditCustomer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditCustomer.
     */
    // TODO: Rename and change types and number of parameters
    public static EditCustomer newInstance(String param1, String param2) {
        EditCustomer fragment = new EditCustomer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_edit_customer, container, false);
        AnhXa(view);
        ButtonActive();
        return view;

    }

    private void ButtonActive() {
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteUserWasLogin();
                Activity activity = getActivity();
                if (activity != null) {
                    activity.finish(); // Kết thúc Fragment hiện tại

                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent); // Khởi chạy Activity mới
                }
            }
        });
    }

    private void AnhXa(View view) {
        helper = new CustomerSQLite(getContext());
        customer = helper.customerWasLogin();
        btnLogOut = view.findViewById(R.id.btnLogout);
    }
}