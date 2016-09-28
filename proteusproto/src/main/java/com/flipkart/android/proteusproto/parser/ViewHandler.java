package com.flipkart.android.proteusproto.parser;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flipkart.android.proteus.builder.LayoutBuilder;
import com.flipkart.android.proteus.providers.AnyViewOrViewGroup;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.providers.Styles;
import com.flipkart.android.proteus.view.ProteusAndroidView;
import com.flipkart.android.proteus.view.ProteusLinearLayout;
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
    public boolean handleAttributes(View view, AnyViewOrViewGroup anyViewOrViewGroup, View parentView) {
        return handleViewAttributes(view, ((ProteusLayout.AnyViewOrViewGroup) anyViewOrViewGroup.getAnyViewOrViewGroup()).getView(), parentView);
    }

    @Override
    boolean handleViewAttributes(View view, ProteusLayout.View viewAttributes, View parentView) {
        //TODO handle View attributes here
        //TODO ---------------------------
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (null != layoutParams) {
            if (0 != viewAttributes.getLayoutParams().getLayoutWidth()) {
                layoutParams.width = viewAttributes.getLayoutParams().getLayoutWidth();
            }
            if (0 != viewAttributes.getLayoutParams().getLayoutHeight()) {
                layoutParams.height = viewAttributes.getLayoutParams().getLayoutHeight();
            }
            view.setLayoutParams(layoutParams);
        }

        if (0 != viewAttributes.getLayoutParams().getWeight()) {
            if (parentView instanceof ProteusLinearLayout) {
                LinearLayout.LayoutParams linearLayoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                linearLayoutParams.weight = viewAttributes.getLayoutParams().getWeight();
                linearLayoutParams.rightMargin = 4;
                linearLayoutParams.leftMargin = 4;
                view.setLayoutParams(linearLayoutParams);
            }
        }

        if (0 != viewAttributes.getBackgroundColor()) {
            view.setBackgroundColor(viewAttributes.getBackgroundColor());
        }

        view.setPadding(viewAttributes.getPaddingLeft(), viewAttributes.getPaddingTop(), viewAttributes.getPaddingRight(), viewAttributes.getPaddingBottom());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && 0 != viewAttributes.getElevation()) {
            view.setElevation(viewAttributes.getElevation());
        }
        return true;
    }

    @Override
    public boolean handleChildLayout(ProteusView view, Layout layout, LayoutBuilder layoutBuilder) {
        return false;
    }

    abstract boolean handleViewGroupAttributes(View view, ProteusLayout.ViewGroup viewGroupAttributes, View parentView);
}
