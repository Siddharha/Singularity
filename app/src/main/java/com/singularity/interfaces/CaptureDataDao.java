package com.singularity.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.singularity.beans.CaptureDataItem;

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

    @Update
    void updateById(CaptureDataItem... captureDataItems);
}
