package com.iblotus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by xiezhiyan on 18-1-30.
 */
@Document(collection = "announce_text")
public class AnnounceText {

    @Id
    private String _id;

    private String companyCd;

    private String companyName;

    private String disclosureTitle;

    private Integer year;

    private String text;

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDisclosureTitle() {
        return disclosureTitle;
    }

    public void setDisclosureTitle(String disclosureTitle) {
        this.disclosureTitle = disclosureTitle;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
