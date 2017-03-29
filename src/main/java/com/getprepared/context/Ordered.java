package com.getprepared.context;

/**
 * Created by koval on 29.03.2017.
 */
public interface Ordered {

    int HIGHEST = Integer.MIN_VALUE;

    int LOWEST = Integer.MAX_VALUE;

    int STEP = 100;

    int getOrder();
}
