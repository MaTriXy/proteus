package com.flipkart.android.proteusproto.parser;

import android.view.View;
import android.view.ViewGroup;

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
    public boolean handleAttributes(View view, AnyViewOrViewGroup anyViewOrViewGroup) {
        return handleViewGroupAttributes(view, ((ProteusLayout.AnyViewOrViewGroup) anyViewOrViewGroup.getAnyViewOrViewGroup()).getViewGroup());
    }

    @Override
    boolean handleViewGroupAttributes(View view, ProteusLayout.ViewGroup viewGroupAttributes) {
        //TODO handle ViewGroup attributes here
        //TODO ---------------------------------

        return super.handleViewAttributes(view, viewGroupAttributes.getView());
    }

    abstract boolean handleFrameLayoutAttributes(View view, ProteusLayout.FrameLayout frameLayoutAttributes);
}
