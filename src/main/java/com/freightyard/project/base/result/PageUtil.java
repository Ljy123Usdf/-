package com.freightyard.project.base.result;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 *
 *@author 梁晋一
 * @date 2020/2/1
 */
public class PageUtil {

    public static PageInfo pageResultInfo(List<?> list) {
        return new PageInfo<>(list);
    }

    public static <T> PageVO<T> pageResultVO(List<T> list) {
        return new PageVO<>(list);
    }

}
