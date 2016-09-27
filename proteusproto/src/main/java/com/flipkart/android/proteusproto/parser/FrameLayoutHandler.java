package com.flipkart.android.proteusproto.parser;

import android.view.View;
import android.view.ViewGroup;

import com.flipkart.android.proteus.providers.AnyViewOrViewGroup;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.providers.Styles;
import com.flipkart.android.proteus.view.ProteusAspectRatioFrameLayout;
import com.flipkart.android.proteus.view.ProteusView;
import com.flipkart.android.proteusproto.models.ProteusLayout;

/**
 * @author kushal.sharma
 */

public class FrameLayoutHandler extends ViewGroupHandler {
    @Override
    public ProteusView createView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        return new ProteusAspectRatioFrameLayout(parent.getContext());
    }

    @Override
    public boolean handleAttributes(View view, AnyViewOrViewGroup anyViewOrViewGroup) {
        return handleFrameLayoutAttributes(view, ((ProteusLayout.AnyViewOrViewGroup) anyViewOrViewGroup.getAnyViewOrViewGroup()).getFrameLayout());
    }

    @Override
    boolean handleFrameLayoutAttributes(View view, ProteusLayout.FrameLayout frameLayoutAttributes) {
        //TODO handle FrameLayout attributes here
        //TODO ----------------------------------

        return super.handleViewGroupAttributes(view, frameLayoutAttributes.getViewGroup());
    }
}
