package com.flipkart.android.proteusproto.parser;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flipkart.android.proteus.builder.LayoutBuilder;
import com.flipkart.android.proteus.providers.AnyViewOrViewGroup;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.providers.Styles;
import com.flipkart.android.proteus.view.ProteusLinearLayout;
import com.flipkart.android.proteus.view.ProteusView;
import com.flipkart.android.proteusproto.models.ProteusLayout;

/**
 * @author kushal.sharma
 */

public class LinearLayoutHandler extends ViewGroupHandler {
    @Override
    public ProteusView createView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        return new ProteusLinearLayout(parent.getContext());
    }

    @Override
    public boolean handleAttributes(View view, AnyViewOrViewGroup anyViewOrViewGroup, View parentView) {
        return handleLayoutAttributes(view, ((ProteusLayout.AnyViewOrViewGroup) anyViewOrViewGroup.getAnyViewOrViewGroup()).getLinearLayout(), parentView);
    }

    @Override
    boolean handleLayoutAttributes(View view, Object layoutAttributes, View parentView) {
        //TODO handle LinearLayout attributes here
        //TODO ----------------------------------

        ProteusLayout.LinearLayout linearLayoutAttributes = (ProteusLayout.LinearLayout) layoutAttributes;
        LinearLayout linearLayout = (LinearLayout) view;

        int orientation = linearLayoutAttributes.getOrientation();
        if (orientation == 0) {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        }
        if (orientation == 1) {
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        }
        return super.handleViewGroupAttributes(view, linearLayoutAttributes.getViewGroup(), parentView);
    }

    @Override
    public boolean handleChildLayout(ProteusView view, Layout layout, LayoutBuilder layoutBuilder) {
        return super.handleChildLayout(view, layout, layoutBuilder);
    }
}
