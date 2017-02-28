package org.maxvigdorchik.floodwarning;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class ListHolder extends AppCompatActivity implements StationFragment.OnListFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_holder);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        StationFragment listfrag = new StationFragment();
        ft.add(R.id.FragmentContainer, listfrag);
        ft.commit();
    }

    public  void onListFragmentInteraction(StationItem item)
    {
        ArrayList<StationItem> stations = new ArrayList<StationItem>();
        Log.e("Lat in transfer",Double.toString(item.lat));
        //stations.add(item);
        Intent myIntent = new Intent(ListHolder.this, MapsActivity.class);
        //myIntent.putParcelableArrayListExtra("stations",(ArrayList<StationItem>) stations);
        myIntent.putExtra("lat",item.lat);
        myIntent.putExtra("lon",item.lon);
        myIntent.putExtra("id",item.id);
        ListHolder.this.startActivity(myIntent);
    }

}
