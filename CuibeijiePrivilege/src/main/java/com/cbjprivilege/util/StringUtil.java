package com.cbjprivilege.util;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static List<Integer> splitToListInt(String str) {
        List<String> strList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
        List<Integer> list = new ArrayList<>();
        for(String integerStr : strList){
        	list.add(Integer.parseInt(integerStr));
        }
        return list;
    }
}
