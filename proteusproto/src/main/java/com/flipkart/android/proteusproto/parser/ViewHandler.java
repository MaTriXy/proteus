package com.flipkart.android.proteusproto.parser;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
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

    public static Drawable getBorderDrawable(int borderWidth, int borderColor, int borderBackgroundColor) {
        float cornerRadius = 0;
        GradientDrawable border = new GradientDrawable();
        border.setCornerRadius(cornerRadius);
        border.setShape(GradientDrawable.RECTANGLE);
        border.setStroke(borderWidth, borderColor);
        border.setColor(borderBackgroundColor);

        return border;
    }

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
                view.setLayoutParams(linearLayoutParams);
            }
        }


        if (0 != viewAttributes.getMarginLeft() && 0 != viewAttributes.getMarginRight()) {
            if (parentView instanceof ProteusLinearLayout) {
                LinearLayout.LayoutParams linearLayoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                linearLayoutParams.rightMargin = 12;
                linearLayoutParams.leftMargin = 12;
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

        if (0 != viewAttributes.getBorderWidth() && 0 != viewAttributes.getBorderColor() && 0 != viewAttributes.getBorderBackgroundColor()) {
            Drawable border = getBorderDrawable(viewAttributes.getBorderWidth(), viewAttributes.getBorderColor(), viewAttributes.getBorderBackgroundColor());
            if (border != null) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    //noinspection deprecation
                    view.setBackgroundDrawable(border);
                } else {
                    view.setBackground(border);
                }
            }
        }
        return true;
    }

    @Override
    public boolean handleChildLayout(ProteusView view, Layout layout, LayoutBuilder layoutBuilder) {
        return false;
    }

    abstract boolean handleViewGroupAttributes(View view, ProteusLayout.ViewGroup viewGroupAttributes, View parentView);
}
