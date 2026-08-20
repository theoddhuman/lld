package com.subham.lld.excel.utility;


import java.util.ArrayList;
import java.util.List;

/**
 * Author: the_odd_human
 * Date: 17/04/25
 */
public class ExpressionUtil {
    public static List<String> tokenize(String str) {
        List<String> tokens = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<str.length(); i++) {
            if(Character.isLetterOrDigit(str.charAt(i))) {
                stringBuilder.append(str.charAt(i));
            } else {
                tokens.add(stringBuilder.toString());
                tokens.add(String.valueOf(str.charAt(i)));
                stringBuilder = new StringBuilder();
            }
        }
        if(!stringBuilder.isEmpty()) {
            tokens.add(stringBuilder.toString());
        }
        return tokens;
    }
}
