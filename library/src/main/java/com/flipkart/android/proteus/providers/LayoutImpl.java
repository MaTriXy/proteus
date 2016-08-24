package com.flipkart.android.proteus.providers;

import java.util.ArrayList;
import java.util.List;

public class LayoutImpl implements Layout {
    private com.flipkart.android.proteus.models.proto.Layout.View view;

    public LayoutImpl(com.flipkart.android.proteus.models.proto.Layout.View layout) {
        this.view = layout;
    }

    @Override
    public String getType() {
        return this.view.type;
    }

    @Override
    public List<Attribute> getAttributeList() {
        return new AttributesImpl(this.view.attributes).getAttributeList();
    }

    @Override
    public List<Layout> getChildren() {
        List<Layout> layoutList = new ArrayList<>();
        if (this.view.view.size() > 0) {
            for (com.flipkart.android.proteus.models.proto.Layout.View view : this.view.view) {
                if (view != null) {
                    layoutList.add(new LayoutImpl(view));
                }
            }
        }
        return layoutList;
    }
}
