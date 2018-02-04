package com.iblotus.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AnnounceReportTask entity.
 */
public class AnnounceReportTaskDTO implements Serializable {

    private String id;

    private String title;

    private Integer year;

    private byte[] companyList;
    private String companyListContentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public byte[] getCompanyList() {
        return companyList;
    }

    public void setCompanyList(byte[] companyList) {
        this.companyList = companyList;
    }

    public String getCompanyListContentType() {
        return companyListContentType;
    }

    public void setCompanyListContentType(String companyListContentType) {
        this.companyListContentType = companyListContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AnnounceReportTaskDTO announceReportTaskDTO = (AnnounceReportTaskDTO) o;
        if(announceReportTaskDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), announceReportTaskDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AnnounceReportTaskDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", year=" + getYear() +
            ", companyList='" + getCompanyList() + "'" +
            "}";
    }
}
