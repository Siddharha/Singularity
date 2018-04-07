package com.singularity.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.singularity.CaptureDataItem;
import com.singularity.CaptureListAdapter;
import com.singularity.R;

import java.util.ArrayList;

public class CapturedDataActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rlCapturedData;
    private CaptureListAdapter captureListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CaptureDataItem> captureDataItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captured_data);
        initialize();
        setupToolbar();
        onActionPerform();
    }

    @Override
    protected void onResume() {
        super.onResume();
        genarateCapturedDate();
    }

    private void genarateCapturedDate() {

        captureDataItems.clear();
        for(int i=0;i<100;i++){
            CaptureDataItem captureDataItem = new CaptureDataItem();
            captureDataItem.setCaptureDataId(i);

            captureDataItems.add(captureDataItem);
        }

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
        toolbar = findViewById(R.id.toolbar);
        rlCapturedData = findViewById(R.id.rlCapturedData);
        captureDataItems = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false);

        captureListAdapter = new CaptureListAdapter(this,R.layout.captured_data_cell,captureDataItems);
        rlCapturedData.setLayoutManager(layoutManager);
        rlCapturedData.setAdapter(captureListAdapter);
    }
}
