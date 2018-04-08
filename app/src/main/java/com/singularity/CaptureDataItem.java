package com.singularity;

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
    private String OwnerContact;

    @ColumnInfo(name = "owner_aadhar_no")
    private String OwnerAadharNo;

    @ColumnInfo(name = "farm_size")
    private String FarmSize;

    @ColumnInfo(name = "crop")
    private String Crop;
    
    @ColumnInfo(name = "crop_stage")
    private String CropStage;

    @ColumnInfo(name = "soil_type")
    private String SoilType;


    public CaptureDataItem(String dateOfRecord, String fieldOwner, String ownerEmailId, String ownerContact, String ownerAadharNo, String FarmSize, String crop, String cropStage, String SoilType, int captureDataId) {
        this.dateOfRecord = dateOfRecord;
        this.fieldOwner = fieldOwner;
        this.ownerEmailId = ownerEmailId;
        OwnerContact = ownerContact;
        OwnerAadharNo = ownerAadharNo;
        FarmSize = FarmSize;
        Crop = crop;
        CropStage = cropStage;
        SoilType = SoilType;
        CaptureDataId = captureDataId;
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
        return OwnerContact;
    }

    public void setOwnerContact(String ownerContact) {
        OwnerContact = ownerContact;
    }

    public String getOwnerAadharNo() {
        return OwnerAadharNo;
    }

    public void setOwnerAadharNo(String ownerAadharNo) {
        OwnerAadharNo = ownerAadharNo;
    }

    public String getFarmSize() {
        return FarmSize;
    }

    public void setFarmSize(String FarmSize) {
        FarmSize = FarmSize;
    }

    public String getCrop() {
        return Crop;
    }

    public void setCrop(String crop) {
        Crop = crop;
    }

    public String getCropStage() {
        return CropStage;
    }

    public void setCropStage(String cropStage) {
        CropStage = cropStage;
    }

    public String getSoilType() {
        return SoilType;
    }

    public void setSoilType(String SoilType) {
        SoilType = SoilType;
    }

    public int getCaptureDataId() {
        return CaptureDataId;
    }

    public void setCaptureDataId(int captureDataId) {
        CaptureDataId = captureDataId;
    }
}
