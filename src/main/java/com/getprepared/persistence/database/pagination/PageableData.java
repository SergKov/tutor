package com.getprepared.persistence.database.pagination;

/**
 * Created by koval on 21.01.2017.
 */
public class PageableData {

    private long currentPage;
    private long numberOfElements;
    private long showElements;

    public long getLimit() {
        return showElements;
    }

    public long getOffset() {
        return (currentPage - 1) * showElements;
    }

    public long getNumberOfPages() {
        return numberOfElements / showElements; // TODO fix rounding bigDecimal devide
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public long getShowElements() {
        return showElements;
    }

    public void setShowElements(long showElements) {
        this.showElements = showElements;
    }
}
