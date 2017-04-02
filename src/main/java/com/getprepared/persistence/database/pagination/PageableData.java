package com.getprepared.persistence.database.pagination;

/**
 * Created by koval on 21.01.2017.
 */
public class PageableData {

    private Long currentPage;
    private Long numberOfPages;
    private Long numberOfElements;

    public Long getLimit() {
        return numberOfElements;
    }

    public Long getOffset() {
        return numberOfElements * (currentPage - 1) + 1;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}
