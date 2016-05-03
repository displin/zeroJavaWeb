package org.smart4j.chapter3.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 *
 * Created by lin on 2016/5/3.
 */
public final class StringUtil {
    /**
     * 判断string是否为空
     *
     * @param string 待判断的字符串
     * @return true-空，false-非空
     */
    public static boolean isEmpty(String string) {
        if (string != null) {
            string = string.trim();
        }

        return StringUtils.isEmpty(string);
    }

    /**
     * 判断string是否为非空
     *
     * @param string 待判断的字符串
     * @return true-非空，false-空
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
}
