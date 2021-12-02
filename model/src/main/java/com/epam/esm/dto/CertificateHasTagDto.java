package com.epam.esm.dto;

public class CertificateHasTagDto {

    private String certificateId;
    private String tagId;

    public CertificateHasTagDto() {}

    public CertificateHasTagDto(String certificateId, String tagId) {
        this.certificateId = certificateId;
        this.tagId = tagId;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
