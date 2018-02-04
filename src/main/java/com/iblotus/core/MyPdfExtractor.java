package com.iblotus.core;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

/**
 * Created by xiezhiyan on 18-1-28.
 */
public class MyPdfExtractor {

    private static final Logger log = LoggerFactory.getLogger(MyPdfExtractor.class);

    private String url;

    public MyPdfExtractor(String url){
        this.url = url;
    }

    public String getText() throws IOException {

        URL url = new URL(this.url);
        PdfReader pdfReader = new PdfReader(url);
        int pageNum = pdfReader.getNumberOfPages();
        log.info("总页数：" + pageNum);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= pageNum; i++){
            if(i > 1){
                sb.append(System.getProperty("line.separator"));
            }
            String s = PdfTextExtractor.getTextFromPage(pdfReader, i);
            sb.append(s);
        }
        return sb.toString();
    }
}
