package com.singularity.activities;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.singularity.R;
import com.singularity.beans.CaptureDataItem;
import com.singularity.database.AppDatabase;

public class CapturedItemForm extends AppCompatActivity {
    private Toolbar toolbar;
    private AppDatabase db;
    private int data_id;
    private EditText etContact,etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captured_item_form);
        initialize();
        getIntentData();
        setupToolbar();
        showDataFromDB();
        onActionPerform();
    }

    private void showDataFromDB() {
        CaptureDataItem data = db.captureDataDao().getDataById(data_id);
        etName.setText(data.getFieldOwner());
        etContact.setText(data.getOwnerContact());
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
        db = Room.databaseBuilder(this,
                AppDatabase.class,
                "Captured")
                .allowMainThreadQueries()
                .build();
        toolbar = findViewById(R.id.toolbar);
        etContact = findViewById(R.id.etContact);
        etName = findViewById(R.id.etName);
    }

    private void setupToolbar() {
        toolbar.setTitle("CAPTU#"+data_id);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
