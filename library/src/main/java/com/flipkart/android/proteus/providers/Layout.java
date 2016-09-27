package com.flipkart.android.proteus.providers;

import java.util.Collection;

/**
 * @author kushal.sharma
 */
public interface Layout {
    String getType();

    AnyViewOrViewGroup getAnyViewOrViewGroup();

    Collection<Layout> getChildren();
}