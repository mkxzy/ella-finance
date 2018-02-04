package com.iblotus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AnnounceReportTask.
 */
@Document(collection = "announce_report_task")
public class AnnounceReportTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("year")
    private Integer year;

    @Field("company_list")
    private byte[] companyList;

    @Field("company_list_content_type")
    private String companyListContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public AnnounceReportTask title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public AnnounceReportTask year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public byte[] getCompanyList() {
        return companyList;
    }

    public AnnounceReportTask companyList(byte[] companyList) {
        this.companyList = companyList;
        return this;
    }

    public void setCompanyList(byte[] companyList) {
        this.companyList = companyList;
    }

    public String getCompanyListContentType() {
        return companyListContentType;
    }

    public AnnounceReportTask companyListContentType(String companyListContentType) {
        this.companyListContentType = companyListContentType;
        return this;
    }

    public void setCompanyListContentType(String companyListContentType) {
        this.companyListContentType = companyListContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnnounceReportTask announceReportTask = (AnnounceReportTask) o;
        if (announceReportTask.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), announceReportTask.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AnnounceReportTask{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", year=" + getYear() +
            ", companyList='" + getCompanyList() + "'" +
            ", companyListContentType='" + getCompanyListContentType() + "'" +
            "}";
    }
}
