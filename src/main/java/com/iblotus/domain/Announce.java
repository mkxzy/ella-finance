package com.iblotus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by xiezhiyan on 18-1-28.
 */
@Document(collection = "announce")
public class Announce {

    @Id
    private String _id;

    private String companyCd;

    private String companyName;

    private String destFilePath;

    private String disclosureTitle;

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

    public String getDestFilePath() {
        return destFilePath;
    }

    public void setDestFilePath(String destFilePath) {
        this.destFilePath = destFilePath;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDisclosureTitle() {
        return disclosureTitle;
    }

    public void setDisclosureTitle(String disclosureTitle) {
        this.disclosureTitle = disclosureTitle;
    }
}
