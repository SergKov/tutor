package com.getprepared.persistence.database.pagination;

/**
 * Created by koval on 21.01.2017.
 */
public class PageableData {

    private Long currentPage;
    private Long pageSize;

    public Long getLimit() {
        return pageSize;
    }

    public Long getOffset() {
        return pageSize * (currentPage - 1) + 1;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
