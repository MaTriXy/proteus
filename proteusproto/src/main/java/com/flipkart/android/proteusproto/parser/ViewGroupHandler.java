package com.flipkart.android.proteusproto.parser;

import android.view.View;
import android.view.ViewGroup;

import com.flipkart.android.proteus.builder.LayoutBuilder;
import com.flipkart.android.proteus.providers.AnyViewOrViewGroup;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.providers.Styles;
import com.flipkart.android.proteus.view.ProteusView;
import com.flipkart.android.proteusproto.models.ProteusLayout;

/**
 * @author kushal.sharma
 */

abstract class ViewGroupHandler extends ViewHandler {
    @Override
    public ProteusView createView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        return null;
    }

    @Override
    public boolean handleAttributes(View view, AnyViewOrViewGroup anyViewOrViewGroup, View parentView) {
        return handleViewGroupAttributes(view, ((ProteusLayout.AnyViewOrViewGroup) anyViewOrViewGroup.getAnyViewOrViewGroup()).getViewGroup(), parentView);
    }

    @Override
    boolean handleViewGroupAttributes(View view, ProteusLayout.ViewGroup viewGroupAttributes, View parentView) {
        //TODO handle ViewGroup attributes here
        //TODO ---------------------------------

        return super.handleViewAttributes(view, viewGroupAttributes.getView(), parentView);
    }

    @Override
    public boolean handleChildLayout(ProteusView view, Layout layout, LayoutBuilder layoutBuilder) {
        ProteusView child;
        child = layoutBuilder.build((ViewGroup) view, layout, null, 0, null);
        addView(view, child);
        return true;
    }

    abstract boolean handleLayoutAttributes(View view, Object layoutAttributes, View parentView);
}
