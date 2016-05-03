package org.smart4j.chapter3.util;

/**
 * 类型转换的工具类
 *
 * Created by lin on 2016/5/3.
 */
public final class CastUtil {

    /**
     * 将object转为字符串，默认返回空字符串
     *
     * @param object 待转换的对象
     * @return 待转换的对象的字符串，默认返回空字符串
     */
    public static String castString(Object object) {
        return CastUtil.castString(object, "");
    }

    /**
     * 将object转为字符串，默认值为defaultValue
     *
     * @param object 待转换的对象
     * @param defaultValue 默认值
     * @return 待转换对象的字符串，默认返回defaultValue
     */
    private static String castString(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }

    /**
     * 将object转为double，默认返回0
     *
     * @param object 待转换的对象
     * @return 待转换的对象的double值，默认返回0
     */
    public static double castDouble(Object object) {
        return CastUtil.castDouble(object, 0);
    }

    /**
     * 将object转为double，默认值为defaultValue
     *
     * @param object 待转换的对象
     * @param defaultValue 默认返回值
     * @return 待转换的对象的double，默认返回defaultValue
     */
    private static double castDouble(Object object, double defaultValue) {
        double value = defaultValue;

        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }

        return value;
    }

    /**
     * 将object转为long，默认返回0
     *
     * @param object 待转换的对象
     * @return 待转换的对象的long值，默认返回0
     */
    public static long castLong(Object object) {
        return CastUtil.castLong(object, 0);
    }

    /**
     * 将object转为long，默认返回defaultValue
     *
     * @param object 待转换的对象
     * @param defaultValue 默认值
     * @return 待转换的对象的long值，默认返回defaultValue
     */
    private static long castLong(Object object, long defaultValue) {
        long value = defaultValue;

        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }

        return value;
    }

    /**
     * 将object转为int，默认返回0
     *
     * @param object 待转换的对象
     * @return 待转换的对象的int值，默认返回0
     */
    public static int castInt(Object object) {
        return CastUtil.castInt(object, 0);
    }

    /**
     * 将object转为int，默认返回defaultValue
     *
     * @param object 待转换的对象
     * @param defaultValue 默认返回值
     * @return 待转换的对象的int值，默认返回defaultValue
     */
    private static int castInt(Object object, int defaultValue) {
        int value = defaultValue;

        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }

        return value;
    }

    /**
     * 将object转为boolean，默认返回false
     *
     * @param object 待转换的对象
     * @return 待转换的对象的boolean值，默认返回false
     */
    public static boolean castBoolean(Object object) {
        return CastUtil.castBoolean(object, false);
    }

    /**
     * 将object转为boolean，默认返回defaultValue
     *
     * @param object 待转换的对象
     * @param defaultValue 默认值
     * @return 待转换的对象的boolean值，默认返回defaultValue
     */
    private static boolean castBoolean(Object object, boolean defaultValue) {
        boolean value = defaultValue;

        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Boolean.parseBoolean(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }

        return value;
    }
}
