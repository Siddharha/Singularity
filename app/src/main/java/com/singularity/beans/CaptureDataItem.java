package com.singularity.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by siddharthamaji on 07/04/18.
 */

@Entity
public class CaptureDataItem {

    @PrimaryKey(autoGenerate = true)
    private int CaptureDataId;

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


    public CaptureDataItem(String dateOfRecord,
                           String fieldOwner, String ownerEmailId,
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
        return CaptureDataId;
    }

    public String getDateOfRecord() {
        return dateOfRecord;
    }

    public String getFieldOwner() {
        return fieldOwner;
    }

    public String getOwnerEmailId() {
        return ownerEmailId;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public String getOwnerAadharNo() {
        return ownerAadharNo;
    }

    public String getFarmSize() {
        return farmSize;
    }

    public String getCrop() {
        return crop;
    }

    public String getCropStage() {
        return cropStage;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setDateOfRecord(String dateOfRecord) {
        this.dateOfRecord = dateOfRecord;
    }

    public void setFieldOwner(String fieldOwner) {
        this.fieldOwner = fieldOwner;
    }

    public void setOwnerEmailId(String ownerEmailId) {
        this.ownerEmailId = ownerEmailId;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public void setOwnerAadharNo(String ownerAadharNo) {
        this.ownerAadharNo = ownerAadharNo;
    }

    public void setFarmSize(String farmSize) {
        this.farmSize = farmSize;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public void setCropStage(String cropStage) {
        this.cropStage = cropStage;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public void setCaptureDataId(int captureDataId) {
        CaptureDataId = captureDataId;
    }
}
