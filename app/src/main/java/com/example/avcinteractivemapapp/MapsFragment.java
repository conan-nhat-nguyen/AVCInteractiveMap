package com.example.avcinteractivemapapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

/*TODO: (FOR THOSE WORKING ON THE GOOGLE MAPS API)
        Tutorial being followed: https://youtu.be/lBW58tPLn-A?list=PLgCYzUzKIBE-SZUrVOsbYMzH7tPigT3gi
        -Restrict API Key; get done before deployment
        -Polish Google Services, GPS, and location permissions
        -Add custom markers to each location (buildings, parking lots, stadium, etc.)
        -Set boundaries (DONE)
        -On marker click open a popup menu with info. about that location
 */

/**
 * DESCRIPTION:
    This class (fragment) is for managing the Google Maps API. All the features that need to be
    added to the map go here. Whenever MainActivity executes, it automatically calls on
    getSupportFragmentManager() which loads the code in this fragment.
    When this class is instantiated, onCreateView() (which is in this class) is called.
    Inside this method, onMapReady() is called. This is where most of the logic for the map goes and
    where code for implementing a new feature related to the map should be written.
 */
public class MapsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                //Set boundary for the map
                final LatLngBounds avcBounds = new LatLngBounds(new LatLng(34.674910, -118.192287), new LatLng(34.682133, -118.183807));
                googleMap.setLatLngBoundsForCameraTarget(avcBounds);

                //Focus AVC and place default marker (uses custom marker)
                LatLng avc = new LatLng(34.6773, -118.1866);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(avc, 17.5f));
                googleMap.addMarker(new MarkerOptions().position(avc).title("Antelope Valley College").icon(BitmapFromVector(getActivity(), R.drawable.marker_icon)));

                //Markers for campus locations. TODO: Add markers to all significant locations
                //UH
                googleMap.addMarker(new MarkerOptions().position(new LatLng(34.6787345742857, -118.18635845710243)).title("UH").icon(BitmapFromVector(getActivity(), R.drawable.marker_icon)));
                //YH
                googleMap.addMarker(new MarkerOptions().position(new LatLng(34.67899187744454, -118.18548358738202)).title("YH").icon(BitmapFromVector(getActivity(), R.drawable.marker_icon)));
                //TE1
                googleMap.addMarker(new MarkerOptions().position(new LatLng(34.678189124166714, -118.18664388328442)).title("TE1").icon(BitmapFromVector(getActivity(), R.drawable.marker_icon)));


                try {
                    googleMap.setMapStyle(
                            MapStyleOptions.loadRawResourceStyle( getActivity(), R.raw.custom_avc_map)
                    );
                } catch(Resources.NotFoundException e){
                    Log.e("JSON", "Can't find style. Error: ", e);
                }



                // When map is loaded
               /* googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        //When something on the map is clicked
                    }
                });*/
            }
        });
        // Return view
        return view;
    }

    //Logic for adding a custom marker (https://www.geeksforgeeks.org/how-to-add-custom-marker-to-google-maps-in-android/#:~:text=For%20adding%20a%20custom%20marker,this%20marker%20to%20our%20Map.)
    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}