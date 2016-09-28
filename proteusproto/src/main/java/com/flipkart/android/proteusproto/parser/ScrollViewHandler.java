package com.flipkart.android.proteusproto.parser;

import android.view.View;
import android.view.ViewGroup;

import com.flipkart.android.proteus.builder.LayoutBuilder;
import com.flipkart.android.proteus.providers.AnyViewOrViewGroup;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.providers.Styles;
import com.flipkart.android.proteus.view.ProteusScrollView;
import com.flipkart.android.proteus.view.ProteusView;
import com.flipkart.android.proteusproto.models.ProteusLayout;

/**
 * @author kushal.sharma
 */

public class ScrollViewHandler extends ViewGroupHandler {

    @Override
    public ProteusView createView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        return new ProteusScrollView(parent.getContext());
    }

    @Override
    public boolean handleAttributes(View view, AnyViewOrViewGroup anyViewOrViewGroup, View parentView) {
        return handleLayoutAttributes(view, ((ProteusLayout.AnyViewOrViewGroup) anyViewOrViewGroup.getAnyViewOrViewGroup()).getScrollView(), parentView);
    }

    @Override
    boolean handleLayoutAttributes(View view, Object layoutAttributes, View parentView) {
        //TODO handle ScrollView attributes here
        //TODO ----------------------------------

        ProteusLayout.ScrollView scrollViewAttributes = (ProteusLayout.ScrollView) layoutAttributes;
        return super.handleViewGroupAttributes(view, scrollViewAttributes.getViewGroup(), parentView);
    }

    @Override
    public boolean handleChildLayout(ProteusView view, Layout layout, LayoutBuilder layoutBuilder) {
        return super.handleChildLayout(view, layout, layoutBuilder);
    }
}
