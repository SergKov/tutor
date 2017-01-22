package com.getprepared.utils.impl;

import java.util.Collection;

/**
 * Created by koval on 19.01.2017.
 */
public class CollectionUtils {

    private CollectionUtils() { }

    public static boolean isEmpty(Collection coll) {
        return coll != null && coll.isEmpty();
    }
}
