package com.flipkart.android.proteusproto.parser;

import android.view.View;
import android.view.ViewGroup;

import com.flipkart.android.proteus.providers.AnyViewOrViewGroup;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.providers.Styles;
import com.flipkart.android.proteus.view.ProteusAndroidView;
import com.flipkart.android.proteus.view.ProteusView;
import com.flipkart.android.proteusproto.models.ProteusLayout;

/**
 * @author kushal.sharma
 */

abstract class ViewHandler extends LayoutHandlerImpl {
    @Override
    public ProteusView createView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        return new ProteusAndroidView(parent.getContext());
    }

    @Override
    public boolean handleAttributes(View view, AnyViewOrViewGroup anyViewOrViewGroup) {
        return handleViewAttributes(view, ((ProteusLayout.AnyViewOrViewGroup) anyViewOrViewGroup.getAnyViewOrViewGroup()).getView());
    }

    @Override
    boolean handleViewAttributes(View view, ProteusLayout.View viewAttributes) {
        //TODO handle View attributes here
        //TODO ---------------------------
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (null != layoutParams && 0 != viewAttributes.getLayoutParams().getLayoutWidth() && 0 != viewAttributes.getLayoutParams().getLayoutHeight()) {
            layoutParams.width = viewAttributes.getLayoutParams().getLayoutWidth();
            layoutParams.height = viewAttributes.getLayoutParams().getLayoutHeight();
            view.setLayoutParams(layoutParams);
        }
        if (0 != viewAttributes.getBackgroundColor()) {
            view.setBackgroundColor(viewAttributes.getBackgroundColor());
        }
        return true;
    }

    abstract boolean handleViewGroupAttributes(View view, ProteusLayout.ViewGroup viewGroupAttributes);
}
