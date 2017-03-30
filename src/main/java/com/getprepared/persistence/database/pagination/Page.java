package com.getprepared.persistence.database.pagination;

/**
 * Created by koval on 21.01.2017.
 */
public class Page implements Pageable {

    private final Long totalCount;
    private final Long pageSize;

    public Page(Long totalCount, Long pageSize) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
    }

    public Long getPageCount() {
        return totalCount / pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }
}
