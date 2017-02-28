package org.maxvigdorchik.floodwarning;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

    private static GoogleMap mMap;
    public static boolean isFirst = true;
    private ArrayList<StationItem> stationList;
    private static double lat,lon;
    private static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(!isFirst)
        {
            Intent stationintent = getIntent();
            //stationList = stationintent.getParcelableArrayListExtra("stations");
            Bundle b = stationintent.getExtras();
            lat = b.getDouble("lat");
            lon = b.getDouble("lon");
            id = b.getString("id");
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        if(!isFirst)
        {
            setupMap(lat,lon,id);
        }
        // Add a marker in Sydney and move the camera
        //TODO: Remove this and replace with the code needed
        isFirst = false;
    }

    public void setupMap(ArrayList<StationItem> stations)
    {
        for(StationItem s :stations)
        {
            LatLng mark = new LatLng(s.lat,s.lon);
            mMap.addMarker(new MarkerOptions().position(mark).title(s.id));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(50,0)));
    }

    public void setupMap(double lat, double lon, String id)
    {
        LatLng mark = new LatLng(lat,lon);
        mMap.addMarker(new MarkerOptions().position(mark).title(id));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(50,0)));
    }


    public void updateStations(View view)
    {
        Intent myintent = new Intent(MapsActivity.this, ListHolder.class);
        MapsActivity.this.startActivity(myintent);
    }

    public  void onListFragmentInteraction(StationItem item)
    {

    }
}
