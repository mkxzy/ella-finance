package com.iblotus.core;

import java.util.HashMap;

/**
 * Created by xiezhiyan on 18-1-29.
 */
public interface QuotaMatcher {

    HashMap<String, Object> match(String text);

    QuotaMatcher getNext();
}
