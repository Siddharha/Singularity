package com.singularity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siddharthamaji on 08/04/18.
 */

@Dao
public interface CaptureDataDao {

    @Query("SELECT * FROM CaptureDataItem")
    List<CaptureDataItem> getAllCapturedData();

    @Insert
    void insertAll(CaptureDataItem... captureDataItems);
}
