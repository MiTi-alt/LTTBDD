package com.example.myapplication.Customer.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Interface.DirectionsApiService;
import com.example.myapplication.Interface.DirectionsResponse;
import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FGoogleMap#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FGoogleMap extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    private Polyline currentPolyline;
    private Marker currentMarker;
    private Marker currentMarker1;
    public FGoogleMap() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param savedInstanceState The saved instance state bundle.
     * @return A new instance of fragment FGoogleMap.
     */
    public static FGoogleMap newInstance(Bundle savedInstanceState) {
        FGoogleMap fragment = new FGoogleMap();
        fragment.setArguments(savedInstanceState);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f_google_map, container, false);
        AnhXa(view);
        return view;
    }

    private void AnhXa(View view) {
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(getArguments());
        mapView.getMapAsync(this);

        ViewGroup.LayoutParams layoutParams = mapView.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = 1000;
        mapView.setLayoutParams(layoutParams);
    }

    @Override

    public void onMapReady(GoogleMap map) {
        googleMap = map;


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // Xử lý sự kiện khi người dùng nhấp vào bản đồ
                moveCameraToLocation(latLng, 15); // 15 là cấp độ zoom mong muốn
            }
        });

        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                // Xử lý sự kiện khi người dùng nhấp và giữ trên bản đồ
                moveCameraToLocation(latLng, 15); // 15 là cấp độ zoom mong muốn
            }
        });

        LatLng currentLocation = new LatLng(10.824053877737864, 106.68665743554365);
        float zoomLevel = 15.0f;
        moveCameraToLocation(currentLocation, zoomLevel);
        currentMarker1 = googleMap.addMarker(new MarkerOptions().position(currentLocation).title("Vị trí hiện tại"));
    }

    private void moveCameraToLocation(LatLng latLng, float zoomLevel) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(zoomLevel)
                .build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        clearMapElements();

        currentMarker = googleMap.addMarker(new MarkerOptions().position(latLng).title("Vị trí hiện tại"));
        LatLng currentLocation = new LatLng(10.82546596021261, 106.68467260078964);
        getDirectionsAndDrawPolyline(currentLocation, latLng);
    }

    private void clearMapElements() {
        // Clear existing marker
        if (currentMarker != null) {
            currentMarker.remove();
        }

        // Clear existing polyline
        if (currentPolyline != null) {
            currentPolyline.remove();
        }
    }

    private void getDirectionsAndDrawPolyline(LatLng origin, LatLng destination) {
        String apiKey = "AIzaSyAtGnDkPoP5NFtdnI18dRdbzq6HRouwDd0";

        if (googleMap == null) {
            // Handle the case where googleMap is null
            Log.e("MapError", "Google Map is null");
            return;
        }

        String originString = origin.latitude + "," + origin.longitude;
        String destinationString = destination.latitude + "," + destination.longitude;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DirectionsApiService apiService = retrofit.create(DirectionsApiService.class);
        Call<DirectionsResponse> call = apiService.getDirections(originString, destinationString, apiKey);

        call.enqueue(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                if (response.isSuccessful()) {
                    DirectionsResponse directionsResponse = response.body();
                    if (directionsResponse != null) {
                        List<LatLng> points = directionsResponse.getRoutePoints();

                        // Draw polyline on the map
                        drawPolyline(points);
                    } else {
                        Log.e("MapError", "directionsResponse is null");
                    }
                } else {
                    // Handle the case where the request was not successful
                    Log.e("MapError", "Directions API request not successful. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                // Handle the case where the request failed
                Log.e("MapError", "Directions API request failed", t);
            }
        });
    }

    private void drawPolyline(List<LatLng> points) {
        if (googleMap == null) {
            // Handle the case where googleMap is null
            return;
        }

        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(points)
                .color(Color.BLUE)
                .width(5);

        // Add polyline to the map
        currentPolyline = googleMap.addPolyline(polylineOptions);
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}