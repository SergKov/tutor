package com.getprepared.infrastructure.pagination;

import com.getprepared.utils.impl.CollectionUtils;

import java.util.List;

/**
 * Created by koval on 21.01.2017.
 */
public class Page<E> {

    private final List<E> content;
    private final Long totalCount;

    public Page(List<E> content, Long totalCount) {
        this.content = content;
        this.totalCount = totalCount;
    }

    public List<E> getContent() {
        return content;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(content);
    }
}
