package com.flipkart.android.proteusproto.providers;

import com.flipkart.android.proteus.providers.AnyViewOrViewGroup;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteusproto.models.ProteusLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author kushal.sharma
 */

class AnyViewOrViewGroupImpl implements AnyViewOrViewGroup<ProteusLayout.AnyViewOrViewGroup> {
    private ProteusLayout.AnyViewOrViewGroup anyViewOrViewGroup;

    AnyViewOrViewGroupImpl(ProteusLayout.AnyViewOrViewGroup anyViewOrViewGroup) {
        this.anyViewOrViewGroup = anyViewOrViewGroup;
    }

    @Override
    public String getViewOrViewGroupType() {
        return anyViewOrViewGroup.getViewOrViewGroupTypeCase().name();
    }

    @Override
    public boolean isViewGroup() {
        switch (getViewOrViewGroupType()) {
            case "LINEARLAYOUT":
            case "FRAMELAYOUT":
            case "VIEWGROUP":
            case "SCROLLVIEW":
                return true;
            default:
                return false;
        }
    }

    @Override
    public Collection<Layout> getViewGroupChildren() {
        List<ProteusLayout.AnyViewOrViewGroup> anyViewOrViewGroupList;
        switch (getViewOrViewGroupType()) {
            case "LINEARLAYOUT":
                anyViewOrViewGroupList = anyViewOrViewGroup.getLinearLayout().getViewGroup().getAnyViewOrViewGroupList();
                break;
            case "FRAMELAYOUT":
                anyViewOrViewGroupList = anyViewOrViewGroup.getFrameLayout().getViewGroup().getAnyViewOrViewGroupList();
                break;
            case "VIEWGROUP":
                anyViewOrViewGroupList = anyViewOrViewGroup.getViewGroup().getAnyViewOrViewGroupList();
                break;
            case "SCROLLVIEW":
                anyViewOrViewGroupList = anyViewOrViewGroup.getScrollView().getViewGroup().getAnyViewOrViewGroupList();
                break;
            default:
                anyViewOrViewGroupList = null;
                break;
        }
        List<Layout> childLayoutList = new ArrayList<>();
        if (null != anyViewOrViewGroupList) {
            for (ProteusLayout.AnyViewOrViewGroup anyViewOrViewGroup : anyViewOrViewGroupList) {
                childLayoutList.add(new LayoutImpl(anyViewOrViewGroup));
            }
        }
        return childLayoutList;
    }

    @Override
    public ProteusLayout.AnyViewOrViewGroup getAnyViewOrViewGroup() {
        return anyViewOrViewGroup;
    }
}
