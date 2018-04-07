package com.singularity.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.singularity.R;

public class CapturedItemForm extends AppCompatActivity {
    private Toolbar toolbar;
    private int data_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captured_item_form);
        initialize();
        getIntentData();
        setupToolbar();
        onActionPerform();
    }

    private void onActionPerform() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getIntentData() {
        data_id = getIntent().getIntExtra("id",0);
    }

    private void initialize() {
        toolbar = findViewById(R.id.toolbar);
    }

    private void setupToolbar() {
        toolbar.setTitle("CAPTU#"+data_id);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
