package com.singularity;

/**
 * Created by siddharthamaji on 07/04/18.
 */

public class CaptureDataItem {
    private String dateOfRecord, fieldOwner,
            ownerEmailId, OwnerContact,
            OwnerAadharNo, FrameSize,
            Crop, CropStage, SoliType;
    private int CaptureDataId;


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

    public String getFrameSize() {
        return FrameSize;
    }

    public void setFrameSize(String frameSize) {
        FrameSize = frameSize;
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

    public String getSoliType() {
        return SoliType;
    }

    public void setSoliType(String soliType) {
        SoliType = soliType;
    }

    public int getCaptureDataId() {
        return CaptureDataId;
    }

    public void setCaptureDataId(int captureDataId) {
        CaptureDataId = captureDataId;
    }
}
