package com.siberiadante.titlelayout.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * StringUtil.
 */
public class StringUtil {

    private StringUtil() {
        super();
    }

    /**
     * 判断字符串是否为空.
     *
     * @param str str
     * @return bool
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * str1 中是否包含str2.
     *
     * @param str1 str1
     * @param str2 str2
     * @return bool
     */
    public static boolean isHaveString(String str1, String str2) {
        return str1.contains(str2);
    }

    /**
     * Convert half-width to full-width.
     * SBC full-width; DBC half-width
     *
     * @param text text
     * @return string
     */
    public static String toFullWidth(String text) {
        char[] c = text.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**
     * 去除特殊字符或将所有中文标号替换为英文标号.
     *
     * @param str str
     * @return string
     */
    public static String removeSpecialString(String str) {
        str = str.replace("【", "[").replace("】", "]")
                .replace("！", "!").replace("：", ":");  // 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


}
