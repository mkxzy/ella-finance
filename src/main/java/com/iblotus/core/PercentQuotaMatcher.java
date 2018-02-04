package com.iblotus.core;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiezhiyan on 18-1-29.
 */
public class PercentQuotaMatcher implements QuotaMatcher {

    private String keyword;

    private PercentQuotaMatcher next;

    public PercentQuotaMatcher(String keyword){
        this.keyword = keyword;
    }

    @Override
    public HashMap<String, Object> match(String text) {
        String pattern = String.format("(%s)(\n|\\s)+(?<a>\\d{2}.\\d{2})%%?(\n|\\s)+(?<b>\\d{2}.\\d{2})%%?", keyword);
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(text);
        HashMap<String, Object> yysr = new HashMap<>();
        if (m.find()) {
            yysr.put("current", m.group("a"));
            yysr.put("before", m.group("b"));
            return yysr;
        }else{
            if(this.getNext() == null){
                return yysr;
            }
            return this.getNext().match(text);
        }
    }

    @Override
    public QuotaMatcher getNext() {
        return this.next;
    }

    public void setNext(PercentQuotaMatcher next) {
        this.next = next;
    }
}
