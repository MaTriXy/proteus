package com.flipkart.android.proteusproto.parser;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flipkart.android.proteus.providers.AnyViewOrViewGroup;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.providers.Styles;
import com.flipkart.android.proteus.view.ProteusImageView;
import com.flipkart.android.proteus.view.ProteusView;
import com.flipkart.android.proteusproto.models.ProteusLayout;

/**
 * @author kushal.sharma
 */

public class ImageViewHandler extends ViewHandler {
    @Override
    public ProteusView createView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        return new ProteusImageView(parent.getContext());
    }

    @Override
    public boolean handleAttributes(View view, AnyViewOrViewGroup anyViewOrViewGroup, View parentView) {
        //TODO handle ImageView attributes here
        //TODO ----------------------------------

        ProteusLayout.AnyViewOrViewGroup viewOrViewGroup = (ProteusLayout.AnyViewOrViewGroup) anyViewOrViewGroup.getAnyViewOrViewGroup();
        ProteusLayout.ImageView imageViewLayoutInfo = viewOrViewGroup.getImageView();

        ImageView imageView = (ImageView) view;
        imageView.setImageResource(imageViewLayoutInfo.getSrc());
        if (imageViewLayoutInfo.getView().getLayoutParams().getLayoutWidth() > 0) {
            imageView.setMaxWidth(imageViewLayoutInfo.getView().getLayoutParams().getLayoutWidth());
        }
        if (imageViewLayoutInfo.getView().getLayoutParams().getLayoutHeight() > 0) {
            imageView.setMaxHeight(imageViewLayoutInfo.getView().getLayoutParams().getLayoutHeight());
        }

        return super.handleViewAttributes(view, imageViewLayoutInfo.getView(), parentView);
    }

    @Override
    boolean handleViewGroupAttributes(View view, ProteusLayout.ViewGroup viewGroupAttributes, View parentView) {
        return false;
    }
}
