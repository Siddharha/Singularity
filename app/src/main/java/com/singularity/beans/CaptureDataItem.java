package com.singularity.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by siddharthamaji on 07/04/18.
 */

@Entity
public class CaptureDataItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int captureDataId;

    @ColumnInfo(name = "date_of_record")
    private String dateOfRecord;

    @ColumnInfo(name = "field_owner")
    private String fieldOwner;

    @ColumnInfo(name = "owner_email_id")
    private String ownerEmailId;

    @ColumnInfo(name = "owner_contact")
    private String ownerContact;

    @ColumnInfo(name = "owner_aadhar_no")
    private String ownerAadharNo;

    @ColumnInfo(name = "farm_size")
    private String farmSize;

    @ColumnInfo(name = "crop")
    private String crop;
    
    @ColumnInfo(name = "crop_stage")
    private String cropStage;

    @ColumnInfo(name = "soil_type")
    private String soilType;

    public CaptureDataItem(String dateOfRecord, String fieldOwner, String ownerEmailId,
                           String ownerContact, String ownerAadharNo, String farmSize,
                           String crop, String cropStage, String soilType) {

        this.dateOfRecord = dateOfRecord;
        this.fieldOwner = fieldOwner;
        this.ownerEmailId = ownerEmailId;
        this.ownerContact = ownerContact;
        this.ownerAadharNo = ownerAadharNo;
        this.farmSize = farmSize;
        this.crop = crop;
        this.cropStage = cropStage;
        this.soilType = soilType;
    }


    public int getCaptureDataId() {
        return captureDataId;
    }

    public void setCaptureDataId(int captureDataId) {
        this.captureDataId = captureDataId;
    }

    public String getDateOfRecord() {
        return dateOfRecord;
    }

    public void setDateOfRecord(String dateOfRecord) {
        this.dateOfRecord = dateOfRecord;
    }

    public String getFieldOwner() {
        return fieldOwner;
    }

    public void setFieldOwner(String fieldOwner) {
        this.fieldOwner = fieldOwner;
    }

    public String getOwnerEmailId() {
        return ownerEmailId;
    }

    public void setOwnerEmailId(String ownerEmailId) {
        this.ownerEmailId = ownerEmailId;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public String getOwnerAadharNo() {
        return ownerAadharNo;
    }

    public void setOwnerAadharNo(String ownerAadharNo) {
        this.ownerAadharNo = ownerAadharNo;
    }

    public String getFarmSize() {
        return farmSize;
    }

    public void setFarmSize(String farmSize) {
        this.farmSize = farmSize;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getCropStage() {
        return cropStage;
    }

    public void setCropStage(String cropStage) {
        this.cropStage = cropStage;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }
}
