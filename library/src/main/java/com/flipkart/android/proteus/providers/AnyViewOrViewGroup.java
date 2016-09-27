package com.flipkart.android.proteus.providers;

import java.util.Collection;

/**
 * @author kushal.sharma
 */

public interface AnyViewOrViewGroup<V> {
    String getViewOrViewGroupType();

    boolean isViewGroup();

    Collection<Layout> getViewGroupChildren();

    V getAnyViewOrViewGroup();
}
