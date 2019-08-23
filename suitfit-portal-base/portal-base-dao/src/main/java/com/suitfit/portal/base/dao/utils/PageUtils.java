package com.suitfit.portal.base.dao.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.portal.model.pojo.vo.common.PageVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Exrickx
 */
public class PageUtils {
    /**
     * Mybatis-Plus分页封装
     *
     * @param page
     * @return
     */
    public static <T> Page<T> initPage(PageVO<T> page) {
        Page<T> p = new Page();
        long pageNumber = page.getCurrent();
        long pageSize = page.getSize();
        String sort = page.getSort();
        String order = page.getOrder();
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        if (StringUtils.isNotBlank(sort)) {
            Boolean isAsc = false;
            if (StringUtils.isBlank(order)) {
                isAsc = false;
            } else {
                if ("desc".equals(order.toLowerCase())) {
                    isAsc = false;
                } else if ("asc".equals(order.toLowerCase())) {
                    isAsc = true;
                }
            }
            p = new Page(pageNumber, pageSize);
            if (isAsc) {
                p.setAsc(sort);
            } else {
                p.setDesc(sort);
            }
        } else {
            p = new Page(pageNumber, pageSize);
        }
        return p;
    }

    public static <T> PageVO<T> fromIpage(IPage page, Class<T> cls) {
        PageVO<T> pageVO = new PageVO<>();
        if (page != null) {
            pageVO.setCurrent(page.getCurrent());
            pageVO.setSize(page.getSize());
            pageVO.setTotal(page.getTotal());
            List<T> records = (List<T>) BeanUtils.convert(page.getRecords(), cls);
            pageVO.setRecords(records);
        }
        return pageVO;
    }

    /**
     * List 分页
     *
     * @param page
     * @param list
     * @return
     */
    public static <T> List<T> listToPage(PageVO<T> page, List<T> list) {
        long pageNumber = page.getCurrent() - 1;
        long pageSize = page.getSize();
        if (pageNumber < 0) {
            pageNumber = 0;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        int fromIndex = (int) (pageNumber * pageSize);
        int toIndex = (int) (pageNumber * pageSize + pageSize);
        if (fromIndex > list.size()) {
            return new ArrayList();
        } else if (toIndex >= list.size()) {
            return list.subList(fromIndex, list.size());
        } else {
            return list.subList(fromIndex, toIndex);
        }
    }
}
