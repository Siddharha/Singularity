package com.singularity.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.singularity.beans.CaptureDataItem;
import com.singularity.beans.CapturedLatLongItem;

import java.util.List;

/**
 * Created by siddharthamaji on 08/04/18.
 */

@Dao
public interface LatLongDataDao {

    @Query("SELECT * FROM CapturedLatLongItem")
    List<CapturedLatLongItem> getAllCapturedLatLongData();

    @Insert
    void insertAll(CapturedLatLongItem... capturedLatLongItems);

    @Update
    void updateById(CapturedLatLongItem... capturedLatLongItems);
}
