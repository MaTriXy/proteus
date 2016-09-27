package com.flipkart.android.proteusproto.providers;

import com.flipkart.android.proteus.providers.AnyViewOrViewGroup;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteusproto.models.ProteusLayout;

import java.util.Collection;

/**
 * @author kushal.sharma
 */

public class LayoutImpl implements Layout {
    private AnyViewOrViewGroupImpl anyViewOrViewGroupImpl;

    public LayoutImpl(ProteusLayout.AnyViewOrViewGroup anyViewOrViewGroup) {
        this.anyViewOrViewGroupImpl = new AnyViewOrViewGroupImpl(anyViewOrViewGroup);
    }

    @Override
    public String getType() {
        return anyViewOrViewGroupImpl.getViewOrViewGroupType();
    }

    @Override
    public AnyViewOrViewGroup getAnyViewOrViewGroup() {
        return anyViewOrViewGroupImpl;
    }

    @Override
    public Collection<Layout> getChildren() {
        return anyViewOrViewGroupImpl.getViewGroupChildren();
    }
}
