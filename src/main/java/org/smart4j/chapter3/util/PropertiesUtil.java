package org.smart4j.chapter3.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性文件读取的工具类
 *
 * Created by lin on 2016/5/3.
 */
public final class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 加载属性文件
     *
     * @param fileName 属性文件名称
     * @return 返回属性对象
     * @throws FileNotFoundException
     */
    public static Properties loadProperties(String fileName) throws FileNotFoundException {
        Properties properties = null;
        InputStream inputStream = null;

        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

            if (inputStream == null) {
                throw new FileNotFoundException(fileName + " file not found.");
            }

            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("load properties file failed.", e); //// TODO: 2016/5/3 此处异常该不该吃掉？
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("close inputStream failed.", e); //// TODO: 2016/5/3 此处异常该不该吃掉？
                }
            }
        }

        return properties;
    }

    /**
     * 获取key对应的字符型属性值，默认返回空字符串
     * @param properties 属性对象
     * @param key 属性key
     * @return 属性key的值
     */
    public static String getString(Properties properties, String key) {
        return getString(properties, key, "");
    }

    /**
     * 获取指定key的属性值，默认值为defaultValue
     *
     * @param properties 属性对象
     * @param key 属性key
     * @param defaultValue 属性key的默认值
     * @return 属性key的值
     */
    private static String getString(Properties properties, String key, String defaultValue) {
        String value = defaultValue;

        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        }

        return value;
    }

    /**
     * 获取key对应的整型属性值，默认返回0
     *
     * @param properties 属性对象
     * @param key 属性key
     * @return 属性key的值
     */
    public static int getInt(Properties properties, String key) {
        return getInt(properties, key, 0);
    }

    /**
     * 获取指定key的属性值，默认值为defaultValue
     *
     * @param properties 属性对象
     * @param key 属性key
     * @param defaultValue 属性key的默认值
     * @return 属性key的值
     */
    private static int getInt(Properties properties, String key, int defaultValue) {
        int value = defaultValue;

        if (properties.containsKey(key)) {
            value = CastUtil.castInt(properties.getProperty(key));
        }

        return value;
    }

    /**
     * 获取key对应的布尔型属性值，默认返回false
     *
     * @param properties 属性对象
     * @param key 属性key
     * @return 属性key的值
     */
    public static boolean getBoolean(Properties properties, String key) {
        return getBoolean(properties, key, false);
    }

    /**
     * 获取指定key的属性值，默认值为defaultValue
     *
     * @param properties 属性对象
     * @param key 属性key
     * @param defaultValue 属性key的默认值
     * @return 属性key的值
     */
    private static boolean getBoolean(Properties properties, String key, boolean defaultValue) {
        boolean value = defaultValue;

        if (properties.containsKey(key)) {
            value = CastUtil.castBoolean(properties.getProperty(key));
        }

        return value;
    }
}
