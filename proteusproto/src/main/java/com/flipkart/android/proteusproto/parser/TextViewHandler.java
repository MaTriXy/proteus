package com.flipkart.android.proteusproto.parser;

import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flipkart.android.proteus.providers.AnyViewOrViewGroup;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.providers.Styles;
import com.flipkart.android.proteus.view.ProteusTextView;
import com.flipkart.android.proteus.view.ProteusView;
import com.flipkart.android.proteusproto.models.ProteusLayout;

/**
 * @author kushal.sharma
 */

public class TextViewHandler extends ViewHandler {
    @Override
    public ProteusView createView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        return new ProteusTextView(parent.getContext());
    }

    @Override
    public boolean handleAttributes(View view, AnyViewOrViewGroup anyViewOrViewGroup, View parentView) {
        //TODO handle TextView attributes here
        //TODO ----------------------------------
        ProteusLayout.AnyViewOrViewGroup viewOrViewGroup = (ProteusLayout.AnyViewOrViewGroup) anyViewOrViewGroup.getAnyViewOrViewGroup();

        ProteusLayout.TextView textViewLayoutInfo = viewOrViewGroup.getTextView();
        TextView textView = (TextView) view;
        if (null != textViewLayoutInfo.getText()) {
            textView.setText(textViewLayoutInfo.getText());
        }
        if (0 != textViewLayoutInfo.getTextSize()) {
            textView.setTextSize(textViewLayoutInfo.getTextSize());
        }
        if (0 != textViewLayoutInfo.getTextBackground()) {
            textView.setBackgroundColor(textViewLayoutInfo.getTextBackground());
        }
        if (17 == textViewLayoutInfo.getGravity()) {
            textView.setGravity(Gravity.CENTER);
        }
        if (5 == textViewLayoutInfo.getGravity()) {
            textView.setGravity(Gravity.END);
        }
        if (textViewLayoutInfo.getIsHtmlText()) {
            textView.setText(Html.fromHtml(textViewLayoutInfo.getText()));
        }
        return super.handleViewAttributes(view, textViewLayoutInfo.getView(), parentView);
    }

    @Override
    boolean handleViewGroupAttributes(View view, ProteusLayout.ViewGroup viewGroupAttributes, View parentView) {
        return false;
    }
}
