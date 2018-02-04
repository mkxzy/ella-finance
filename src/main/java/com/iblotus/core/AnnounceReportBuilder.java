package com.iblotus.core;

import com.iblotus.domain.AnnounceReport;

/**
 * Created by xiezhiyan on 18-1-29.
 */
public class AnnounceReportBuilder {

    private AnnounceReport announceReport = new AnnounceReport();

    public AnnounceReportBuilder(){
    }

    public void buildCompany(String cd, String name){
        announceReport.setCompanyCd(cd);
        announceReport.setCompanyName(name);
    }

    public void buildYear(Integer year){
        this.announceReport.setYear(year);
    }

    public void buildYssr(String text){
        TwoLineQuotaMatcher matcher = new TwoLineQuotaMatcher("营业收入");
        announceReport.setYysr(matcher.match(text));
    }

    public void buildZcfzl(String text){
        TwoLineQuotaMatcher matcher = new TwoLineQuotaMatcher("资产负债率%（合并）");
        matcher.setNext(new PercentQuotaMatcher("资产负债率%"));
        announceReport.setZcfzl(matcher.match(text));
    }

    public AnnounceReport getResult(){
        return this.announceReport;
    }
}
