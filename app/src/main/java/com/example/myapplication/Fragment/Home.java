package com.example.myapplication.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView btnYoutobe,btnGoogleMap,btnQR;
    private ImageView imageHome;
    private TextView textSuKien;
    private ViewPager viewPager;
    private HorizontalScrollView horizontalScrollView;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        AnhXa(view);
        ButtonActive();
        return view;
    }

    private void ButtonActive() {
        btnYoutobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo instance của Fragment mới
                imageHome.setVisibility(View.GONE);
                horizontalScrollView.setVisibility(View.GONE);
                Youtobe fragment = new Youtobe();
                // Lấy đối tượng FragmentManager
                FragmentManager fragmentManager = getParentFragmentManager();

                // Bắt đầu giao dịch Fragment
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Thay thế Fragment hiện tại trong GridLayout bằng Fragment mới
                fragmentTransaction.replace(R.id.gridLayout, fragment);

                // Kết thúc giao dịch Fragment
                fragmentTransaction.commit();

            }
        });
        btnGoogleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo instance của Fragment mới

                textSuKien.setText("Google map");
                imageHome.setVisibility(View.GONE);
                horizontalScrollView.setVisibility(View.GONE);
                FGoogleMap fragment = new FGoogleMap();
                // Lấy đối tượng FragmentManager
                FragmentManager fragmentManager = getParentFragmentManager();

                // Bắt đầu giao dịch Fragment
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Thay thế Fragment hiện tại trong GridLayout bằng Fragment mới
                fragmentTransaction.replace(R.id.gridLayout, fragment);

                // Kết thúc giao dịch Fragment
                fragmentTransaction.commit();

            }
        });
        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo instance của Fragment mới

                textSuKien.setText("QR code");
                imageHome.setVisibility(View.GONE);
                horizontalScrollView.setVisibility(View.GONE);
                QRCode fragment = new QRCode();
                // Lấy đối tượng FragmentManager
                FragmentManager fragmentManager = getParentFragmentManager();

                // Bắt đầu giao dịch Fragment
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Thay thế Fragment hiện tại trong GridLayout bằng Fragment mới
                fragmentTransaction.replace(R.id.gridLayout, fragment);

                // Kết thúc giao dịch Fragment
                fragmentTransaction.commit();

            }
        });
    }

    private void AnhXa(View view) {
        textSuKien = view.findViewById(R.id.textSuKien);
        viewPager= view.findViewById(R.id.view_pager);
        btnYoutobe = view.findViewById(R.id.btnYoutobe);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        imageHome = view.findViewById(R.id.imageHome);
        btnGoogleMap= view.findViewById(R.id.btnGoogleMap);
        btnQR = view.findViewById(R.id.btnQR);
    }

}