package com.singularity.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by siddhartha on 10/4/18.
 */
@Entity
public class CapturedLatLongItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int locationId;

    @ColumnInfo(name = "picked_latitude")
    private double lat;

    @ColumnInfo(name = "picked_longitude")
    private double lon;

    public CapturedLatLongItem(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
