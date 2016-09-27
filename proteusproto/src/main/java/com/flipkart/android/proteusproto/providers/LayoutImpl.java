package com.flipkart.android.proteusproto.providers;

import com.flipkart.android.proteus.providers.AnyViewOrViewGroup;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteusproto.models.ProteusLayout;

import java.util.Collection;

/**
 * @author kushal.sharma
 */

public class LayoutImpl implements Layout {
    private AnyViewOrViewGroupImpl anyViewOrViewGroup;

    public LayoutImpl(ProteusLayout.AnyViewOrViewGroup anyViewOrViewGroup) {
        this.anyViewOrViewGroup = new AnyViewOrViewGroupImpl(anyViewOrViewGroup);
    }

    @Override
    public String getType() {
        return anyViewOrViewGroup.getViewOrViewGroupType();
    }

    @Override
    public AnyViewOrViewGroup getAnyViewOrViewGroup() {
        return anyViewOrViewGroup;
    }

    @Override
    public Collection<Layout> getChildren() {
        return anyViewOrViewGroup.isViewGroup() ? anyViewOrViewGroup.getViewGroupChildren() : null;
    }
}
