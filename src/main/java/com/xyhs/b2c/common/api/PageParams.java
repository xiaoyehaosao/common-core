package com.xyhs.b2c.common.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.xyhs.b2c.common.constant.MyConstants;
import com.xyhs.b2c.common.utils.BeanConvertUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * 分页参数
 *
 * @author liuyau
 * @date 2018/07/10
 */
public class PageParams<T> extends Page<T> implements Serializable {
    private static final long serialVersionUID = -1710273706052960025L;
    private int page = MyConstants.DEFAULT_PAGE;
    private int limit = MyConstants.DEFAULT_LIMIT;
    private String sort;
    private String order;
    private QueryWrapper<T> entity;
    private Map<String, Object> requestMap = Maps.newHashMap();
    /**
     * 排序
     */
    private String orderBy;

    public PageParams() {
        requestMap = Maps.newHashMap();
    }

    public PageParams(Map<String, Object> map) {
        if (map == null) {
            map = Maps.newHashMap();
        }
        this.page = Integer.parseInt(map.getOrDefault(MyConstants.PAGE_KEY, MyConstants.DEFAULT_PAGE).toString());
        this.limit = Integer.parseInt(map.getOrDefault(MyConstants.PAGE_LIMIT_KEY, MyConstants.DEFAULT_LIMIT).toString());
        this.sort = (String) map.getOrDefault(MyConstants.PAGE_SORT_KEY, "");
        this.order = (String) map.getOrDefault(MyConstants.PAGE_ORDER_KEY, "");
        super.setCurrent(page);
        super.setSize(limit);
        map.remove(MyConstants.PAGE_KEY);
        map.remove(MyConstants.PAGE_LIMIT_KEY);
        map.remove(MyConstants.PAGE_SORT_KEY);
        map.remove(MyConstants.PAGE_ORDER_KEY);
        requestMap.putAll(map);
    }
    public PageParams(Map<String, Object> map,Class<T> t) {
        if (map == null) {
            map = Maps.newHashMap();
        }
        this.page = Integer.parseInt(map.getOrDefault(MyConstants.PAGE_KEY, MyConstants.DEFAULT_PAGE).toString());
        this.limit = Integer.parseInt(map.getOrDefault(MyConstants.PAGE_LIMIT_KEY, MyConstants.DEFAULT_LIMIT).toString());
        this.sort = (String) map.getOrDefault(MyConstants.PAGE_SORT_KEY, "");
        this.order = (String) map.getOrDefault(MyConstants.PAGE_ORDER_KEY, "");
        super.setCurrent(page);
        super.setSize(limit);
        map.remove(MyConstants.PAGE_KEY);
        map.remove(MyConstants.PAGE_LIMIT_KEY);
        map.remove(MyConstants.PAGE_SORT_KEY);
        map.remove(MyConstants.PAGE_ORDER_KEY);
        requestMap.putAll(map);
        this.entity = new QueryWrapper<>(BeanConvertUtils.mapToObject(this.requestMap, t));
    }




    public PageParams(int page, int limit) {
        this(page, limit, "", "");
    }

    public PageParams(int page, int limit, String sort, String order) {
        this.page = page;
        this.limit = limit;
        this.sort = sort;
        this.order = order;
    }

    public int getPage() {
        if (page <= MyConstants.MIN_PAGE) {
            page = 1;
        }
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        if (limit > MyConstants.MAX_LIMIT) {
            limit = MyConstants.MAX_LIMIT;
        }
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderBy() {
        if (StringUtils.isEmpty(order)) {
            order = "asc";
        }
        if (StringUtils.isNotEmpty(sort)) {
            this.setOrderBy(String.format("%s %s", StringUtils.camelToUnderline(sort), order));
        }
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public <T> T mapToObject(Class<T> t) {
        return BeanConvertUtils.mapToObject(this.requestMap, t);
    }

    public Map<String, Object> getRequestMap() {
        return requestMap;
    }

    public void setRequestMap(Map<String, Object> requestMap) {
        this.requestMap = requestMap;
    }

    public QueryWrapper<T> getEntity() {
        return entity;
    }

    public void setEntity(QueryWrapper<T> entity) {
        this.entity = entity;
    }
}
