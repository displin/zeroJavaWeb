package org.smart4j.chapter3.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 *
 * Created by lin on 2016/5/3.
 */
public final class ColletionUtil {
    /**
     * 判断collection是否为空
     *
     * @param collection 待判断的集合
     * @return true-空, false-非空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断collection是否为非空
     *
     * @param collection 待判断的集合
     * @return true-非空, false-空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断map是否为空
     *
     * @param map 待判断的map
     * @return true-空, false-非空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断map是否为非空
     *
     * @param map 待判断的map
     * @return true-非空, false-空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
}
