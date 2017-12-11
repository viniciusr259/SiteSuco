package br.com.sitesuco.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    long longitude;
    long latitude;
    LatLng NossaLoja;

    local c =  MainActivity.c;



    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        longitude = c.getLongitude();
        latitude = c.getLatitude();
        Log.d("latitude :", String.valueOf(longitude));
        NossaLoja = new LatLng(latitude,longitude);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        //FOCO EM SP
        mMap.moveCamera(CameraUpdateFactory.newLatLng(NossaLoja));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(7));
        mMap.addMarker(new MarkerOptions().position(NossaLoja)
                .title("Nossa Loja"));
    }
}
