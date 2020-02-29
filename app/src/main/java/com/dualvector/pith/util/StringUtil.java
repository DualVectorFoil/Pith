package com.dualvector.pith.util;

import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern mChinesePattern = Pattern.compile("[\u4e00-\u9fa5]");
    private static final Pattern mLetterDigitOrChinesePattern = Pattern.compile("^[a-z0-9A-Z\u4e00-\u9fa5]+$");

    public static boolean isContainChinese(String s) {
        return mChinesePattern.matcher(s).find();
    }

    public static boolean isContainSpecialChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLowerCase(s.charAt(i)) && !Character.isUpperCase(s.charAt(i)) && !Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLetterDigitOrChinese(String s) {
        return mLetterDigitOrChinesePattern.matcher(s).find();
    }
}
