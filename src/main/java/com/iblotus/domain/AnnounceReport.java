package com.iblotus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by xiezhiyan on 18-1-29.
 */
@Document(collection = "announce_report")
public class AnnounceReport implements Serializable {

    @Id
    private String _id;

    private String companyCd;

    private Integer year;

    private String companyName;

    private HashMap<String, Object> yysr;

    private HashMap<String, Object> zcfzl;

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public HashMap<String, Object> getYysr() {
        return yysr;
    }

    public void setYysr(HashMap<String, Object> yysr) {
        this.yysr = yysr;
    }

    public HashMap<String, Object> getZcfzl() {
        return zcfzl;
    }

    public void setZcfzl(HashMap<String, Object> zcfzl) {
        this.zcfzl = zcfzl;
    }
}
