package com.flipkart.android.proteus.providers;

import java.util.List;

public interface Layout {
    String getType();

    List<Attribute> getAttributeList();

    List<Layout> getChildren();
}
