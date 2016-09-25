package com.flipkart.android.proteus.providers;

import java.util.Collection;

/**
 * @author kushal.sharma
 */
public interface Layout {
    String getType();

    Collection<AttributeKeyValue> getAttributes();

    Collection<Layout> getChildren();
}
