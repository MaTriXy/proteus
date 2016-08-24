package com.flipkart.android.proteus.providers;

import com.flipkart.android.proteus.models.proto.Layout;

public interface Attribute {
    String getType();

    String getKey();

    Layout.Value getValue();
}
