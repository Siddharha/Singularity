package com.singularity.activities;

import android.Manifest;
import android.animation.Animator;
import android.app.ActivityOptions;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.singularity.beans.CapturedLatLongItem;
import com.singularity.database.AppDatabase;
import com.singularity.beans.CaptureDataItem;
import com.singularity.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private FrameLayout flLoader;
    private int RQS_GooglePlayServices = 101;
    private GoogleMap gmap;
    private FloatingActionButton fbMenu;
    private boolean IS_DRAWING_MODE;
    private RelativeLayout flDrawingMode;
    private FloatingActionButton btnDrawMap;
    private AppDatabase db;
    private ArrayList<Point> points;
    private ArrayList<LatLng> latLngs;
    private String TAG = "style_map";
    private ImageView imgMenu;
    private DrawerLayout dvMenu;
    private LinearLayout llCapturedData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTransition();
        setContentView(R.layout.activity_map);
        initialize();
        checkPlaystervice();
        showMap();
        animateMapApear();
        onActionPerform();
    }

    private void onActionPerform() {
        fbMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!IS_DRAWING_MODE){
                    fbMenu.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                    gmap.getUiSettings().setScrollGesturesEnabled(false);
                    flDrawingMode.setVisibility(View.VISIBLE);
                    flDrawingMode.animate().alpha(1);
                    IS_DRAWING_MODE = true;
                }else {
                    fbMenu.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                    IS_DRAWING_MODE = false;
                    gmap.getUiSettings().setScrollGesturesEnabled(true);

                    flDrawingMode.animate().alpha(0);
                    clearChildPointsAndPointData();

                }
            }
        });

        flDrawingMode.animate().setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(!IS_DRAWING_MODE){
                    flDrawingMode.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

flDrawingMode.setOnTouchListener(handleTouch);

btnDrawMap.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if (points.size() > 0) {
            for (Point point : points) {
                latLngs.add(gmap.getProjection().fromScreenLocation(point));

            }

            addDataToDb(latLngs);

            drawPoly();
        }else {
            fbMenu.performClick();
        }
    }
});

imgMenu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dvMenu.openDrawer(Gravity.LEFT);
    }
});

        llCapturedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dvMenu.closeDrawer(Gravity.LEFT);
               Intent intent = new Intent(getBaseContext(),CapturedDataActivity.class);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.
                            makeSceneTransitionAnimation(MapActivity.this,
                                    llCapturedData,
                                    "cptData");
                    // start the new activity
                    startActivity(intent, options.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });
    }

    private void addDataToDb(ArrayList<LatLng> latLngs) {

        db.captureDataDao()
                .insertAll(new CaptureDataItem(getDateTimeStamp(),
                "",
                "",
                "",
                "",
                "",
                "","",""));

        List<CaptureDataItem> datas = db.captureDataDao().getAllCapturedData();
        for(LatLng ltln: latLngs) {
            db.latLongDataDao().
                    insertAll(new CapturedLatLongItem(datas.get(datas.size()-1).
                            getCaptureDataId(),ltln.latitude,ltln.longitude));
        }
    }

    private String getDateTimeStamp() {
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return s.format(new Date());
    }

    private void clearChildPointsAndPointData() {
        latLngs.clear();
        points.clear();

        flDrawingMode.removeViews(1,flDrawingMode.getChildCount()-1);
    }

    private void drawPoly() {
        PolylineOptions polylineOptions = new PolylineOptions().geodesic(true).width(5).color(Color.BLACK);
        polylineOptions.addAll(latLngs);
        polylineOptions.add(latLngs.get(0));

        Polyline line = gmap.addPolyline(polylineOptions);
        clearChildPointsAndPointData();
    }

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ImageView imageView;

            int x = (int) event.getX();
            int y = (int) event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.i("TAG", "touched down");

                        imageView = new ImageView(getBaseContext());
                        imageView.setImageResource(R.drawable.points);
                        imageView.setScaleType(ImageView.ScaleType.CENTER);

                        flDrawingMode.addView(imageView);
                        imageView.setX(x);
                        imageView.setY(y);

                        points.add(new Point(x,y));
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.i("TAG", "moving: (" + x + ", " + y + ")");

                    break;
                case MotionEvent.ACTION_UP:
                    Log.i("TAG", "touched up");
                    break;
            }

            return true;
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        mapFragment.onResume();
    }


    private void activityTransition() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
        }
    }

    private void checkPlaystervice() {
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (status == ConnectionResult.SUCCESS) {
            //alarm to go and install Google Play Services
        } else if (status == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED) {
            // Toast.makeText(this,"please udpate your google play service",Toast.LENGTH_SHORT).show();
           // GooglePlayServicesUtil.getErrorDialog(status, this, RQS_GooglePlayServices).show();
            GoogleApiAvailability.getInstance().getErrorDialog(this,status,RQS_GooglePlayServices).show();
        }
    }

    private void initialize() {
        db = Room.databaseBuilder(this,
                AppDatabase.class,
                "Captured")
                .allowMainThreadQueries()
                .build();
        llCapturedData = findViewById(R.id.llCapturedData);
        dvMenu = findViewById(R.id.dvMenu);
        imgMenu = findViewById(R.id.imgMenu);
        points = new ArrayList<>();
        latLngs = new ArrayList<>();
        btnDrawMap = findViewById(R.id.btnDrawMap);
        flDrawingMode = findViewById(R.id.flDrawingMode);
        flDrawingMode.setVisibility(View.GONE);
        flDrawingMode.setAlpha(0);
        IS_DRAWING_MODE = false;
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        flLoader = findViewById(R.id.flLoader);
        fbMenu = findViewById(R.id.fbMenu);
    }

    private void showMap() {
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setRotateGesturesEnabled(false);

        //Toast.makeText(this, "Map Loaded!", Toast.LENGTH_SHORT).show();
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_style));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //googleMap.setMyLocationEnabled(true);
        //LatLng myLocation

        gmap = googleMap;

        View locationButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();


        ViewGroup parent = (ViewGroup) mapFragment.getView().findViewById(Integer.parseInt("1")).getParent();
        View compassButton = parent.getChildAt(4);


        // position on right bottom
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.setMargins(0, 0, 30, 30);

        RelativeLayout.LayoutParams CompRlp = (RelativeLayout.LayoutParams) compassButton.getLayoutParams();
        CompRlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        CompRlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        CompRlp.setMargins(30, 30, 0, 0);



    }

    private void animateMapApear() {
        flLoader.animate().alpha(0).setDuration(2000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                flLoader.setVisibility(View.GONE);
                focusToMyLocation();

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void focusToMyLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        assert locationManager != null;
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));

        if (location != null)
        {
            gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(12)                   // Sets the zoom
                    .bearing(0)                // Sets the orientation of the camera to east
                    .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }


}
