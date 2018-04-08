package com.singularity.activities;

import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.Window;

import com.singularity.AppDatabase;
import com.singularity.CaptureDataItem;
import com.singularity.CaptureListAdapter;
import com.singularity.R;

import java.util.ArrayList;

public class CapturedDataActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rlCapturedData;
    private CaptureListAdapter captureListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    //private ArrayList<CaptureDataItem> captureDataItems;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTransition();
        setContentView(R.layout.activity_captured_data);
        initialize();
        setupToolbar();
        onActionPerform();
    }

    private void activityTransition() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        genarateCapturedDate();
    }

    private void genarateCapturedDate() {

      //  captureDataItems.clear();
        /*for(int i=0;i<100;i++){
            CaptureDataItem captureDataItem = new CaptureDataItem("","",
                    "","","","","","","",i);

            captureDataItems.add(captureDataItem);
        }
*/
        captureListAdapter.notifyDataSetChanged();
    }

    private void onActionPerform() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setupToolbar() {
        toolbar.setTitle("Captured Data");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initialize() {
        db = Room.databaseBuilder(this,
                AppDatabase.class,
                "Captured")
                .allowMainThreadQueries()
                .build();

        toolbar = findViewById(R.id.toolbar);
        rlCapturedData = findViewById(R.id.rlCapturedData);
       // captureDataItems = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false);

        captureListAdapter = new CaptureListAdapter(this,
                        R.layout.captured_data_cell,
                        db.captureDataDao().getAllCapturedData());

        rlCapturedData.setLayoutManager(layoutManager);
        rlCapturedData.setAdapter(captureListAdapter);
    }
}
