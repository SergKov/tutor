package com.getprepared.context.core.postprocess;

/**
 * Created by koval on 29.03.2017.
 */
public interface Ordered {

    int STEP = 100;

    int getOrder();
}