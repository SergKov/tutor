package com.getprepared.infrastructure.pagination;

/**
 * Created by koval on 13.01.2017.
 */
public interface Pageable {

    Pageable first();

    Pageable next();

    int getOffset();

    int getPageNumber();

    int getPageSize();

    SortOrder getSortOrder();
}
