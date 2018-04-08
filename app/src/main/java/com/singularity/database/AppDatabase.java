package com.singularity.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.singularity.interfaces.CaptureDataDao;
import com.singularity.beans.CaptureDataItem;

/**
 * Created by siddharthamaji on 08/04/18.
 */

@Database(entities = {CaptureDataItem.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CaptureDataDao captureDataDao();
}
