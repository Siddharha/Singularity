package com.singularity.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.singularity.beans.CapturedLatLongItem;
import com.singularity.interfaces.CaptureDataDao;
import com.singularity.beans.CaptureDataItem;
import com.singularity.interfaces.LatLongDataDao;

/**
 * Created by siddharthamaji on 08/04/18.
 */

@Database(entities = {CaptureDataItem.class,CapturedLatLongItem.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CaptureDataDao captureDataDao();
    public abstract LatLongDataDao latLongDataDao();
}
