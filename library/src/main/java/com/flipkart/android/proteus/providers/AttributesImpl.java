package com.flipkart.android.proteus.providers;

import com.flipkart.android.proteus.models.proto.Layout;

import java.util.ArrayList;
import java.util.List;

public class AttributesImpl implements Attributes {
    List<Layout.Attributes> attributes;

    public AttributesImpl(List<Layout.Attributes> attributes) {
        this.attributes = attributes;
    }

    @Override
    public List<Attribute> getAttributeList() {
        List<Attribute> attributeList = new ArrayList<>();
        for (Layout.Attributes attribute : attributes) {
            attributeList.add(new AttributeImpl(attribute.type, attribute.key, attribute.value));
        }
        return attributeList;
    }
}
