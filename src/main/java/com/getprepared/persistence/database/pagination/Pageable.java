package com.getprepared.persistence.database.pagination;

import java.util.List;

/**
 * Created by koval on 30.03.2017.
 */
public interface Pageable<E> {

    Long getPageCount();

    boolean isEmpty();

    List<E> getContent();

    Long getTotalCount();
}
