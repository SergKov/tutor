package com.getprepared.infrastructure.pagination;

import com.getprepared.utils.impl.CollectionUtils;

import java.util.List;

/**
 * Created by koval on 21.01.2017.
 */
public class Page<E> {

    private final List<E> content;
    private final Long totalCount;
    private final Long pageIndex;
    private final Long pageSize;

    public Page(List<E> content, Long totalCount, Long pageIndex, Long pageSize) {
        this.content = content;
        this.totalCount = totalCount;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Long getPageCount() {
        return totalCount / pageSize;
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(content);
    }

    public List<E> getContent() {
        return content;
    }

    public Long getTotalCount() {
        return totalCount;
    }
}
