package com.xyhs.b2c.common.api;

import com.xyhs.b2c.common.constant.MyConstants;
import lombok.Data;

import java.io.Serializable;

/**
 * @author DELL
 */
@Data
public class QueryParams<T>   implements Serializable {

    private static final long serialVersionUID = -2243316181669185742L;
    private int page = MyConstants.DEFAULT_PAGE;
    private int limit = MyConstants.DEFAULT_LIMIT;
    private String sort;
    private String order;
    private T entity;
}
