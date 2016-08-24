package com.flipkart.android.proteus.providers;

import com.flipkart.android.proteus.models.proto.Layout;

public class AttributeImpl implements Attribute {
    private String type;
    private String key;
    private Layout.Value value;

    public AttributeImpl(String type, String key, Layout.Value value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Layout.Value getValue() {
        return value;
    }
}
