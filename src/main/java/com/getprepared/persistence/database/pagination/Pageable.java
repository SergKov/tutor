package com.getprepared.persistence.database.pagination;

/**
 * Created by koval on 30.03.2017.
 */
public interface Pageable {

    Long getPageCount();

    Long getTotalCount();
}
