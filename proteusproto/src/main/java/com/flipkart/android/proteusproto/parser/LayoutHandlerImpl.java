package com.flipkart.android.proteusproto.parser;

import android.content.res.XmlResourceParser;
import android.view.View;
import android.view.ViewGroup;

import com.flipkart.android.proteus.parser.AttributeProcessor;
import com.flipkart.android.proteus.parser.LayoutHandler;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.providers.Styles;
import com.flipkart.android.proteus.view.ProteusView;
import com.flipkart.android.proteusproto.models.ProteusLayout;
import com.google.protobuf.Parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * @author kushal.sharma
 */

abstract class LayoutHandlerImpl<V extends View> implements LayoutHandler<V> {
    private static XmlResourceParser sParser = null;

    @Override
    public void onBeforeCreateView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {

    }

    @Override
    public void onAfterCreateView(V view, ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        ViewGroup.LayoutParams layoutParams = null;
        try {
            layoutParams = generateDefaultLayoutParams(parent);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        view.setLayoutParams(layoutParams);
    }

    @Override
    public void prepareAttributeHandlers() {

    }

    @Override
    public void addHandler(String attributeKey, AttributeProcessor handler) {

    }

    @Override
    public boolean handleChildLayout(ProteusView view, Layout layout) {
        return false;
    }

    @Override
    public boolean addView(ProteusView parent, ProteusView view) {
        return false;
    }

    ViewGroup.LayoutParams generateDefaultLayoutParams(ViewGroup parent) throws IOException, XmlPullParserException {

        /**
         * This whole method is a hack! To generate layout params, since no other way exists.
         * Refer : http://stackoverflow.com/questions/7018267/generating-a-layoutparams-based-on-the-type-of-parent
         */
        if (null == sParser) {
            synchronized (Parser.class) {
                if (null == sParser) {
                    sParser = parent.getResources().getLayout(com.flipkart.android.proteus.R.layout.layout_params_hack);
                    //noinspection StatementWithEmptyBody
                    while (sParser.nextToken() != XmlPullParser.START_TAG) {
                        // Skip everything until the view tag.
                    }
                }
            }
        }

        return parent.generateLayoutParams(sParser);
    }

    abstract boolean handleViewAttributes(V view, ProteusLayout.View viewAttributes);
}
