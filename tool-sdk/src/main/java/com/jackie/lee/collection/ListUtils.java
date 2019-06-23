package com.jackie.lee.collection;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 操作列表的功能集合
 *
 * 使用Case:
 * List<T> result = ListUtils.filterNullItems(itemList);
 * List<T> result = ListUtils.filter(itemList, filter);
 * List<T> result = ListUtils.filter(itemList, filterList);
 */
public class ListUtils {

    private static Filter nullItemFilter = item -> {
        if (item == null) {
            return true;
        }
        return false;
    };

    /**
     * 过滤列表中null的元素
     *
     * @param itemList
     * @param <T>
     * @return
     */
    public static <T> List<T> filterNullItems(List<T> itemList) {
        return filter(itemList, nullItemFilter);
    }

    /**
     * 根据传入的过滤器过滤列表中的元素
     *
     * @param itemList
     * @param filter
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> itemList, Filter<T> filter) {
        return filter(itemList, Lists.newArrayList(filter));
    }

    /**
     * 根据传入的过滤器列表过滤列表中的元素,如果传入的元素列表为null或者为空, 则返回空列表
     *
     * 注意: 返回的列表类型都属于ArrayList
     *
     * @param itemList
     * @param filterList
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> itemList, List<Filter<T>> filterList) {
        Assert.isTrue(CollectionUtils.isNotEmpty(filterList), "传入的过滤器列表不能为空");

        if (CollectionUtils.isEmpty(itemList)) {
            return Lists.newArrayList();
        }

        List<T> result = Lists.newArrayList();
        for (T item : itemList) {
            if (needFilter(filterList, item)) {
                continue;
            }
            result.add(item);
        }
        return result;
    }

    private static <T> boolean needFilter(List<Filter<T>> filterList, T item) {
        for (Filter<T> filter : filterList) {
            if (filter.needFilter(item)) {
                return true;
            }
        }
        return false;
    }

    public interface Filter<T> {

        /**
         * 当前元素是否需要过滤
         *
         * @param item
         * @return true会被过滤;否则不会被过滤
         */
        boolean needFilter(T item);

    }

}
