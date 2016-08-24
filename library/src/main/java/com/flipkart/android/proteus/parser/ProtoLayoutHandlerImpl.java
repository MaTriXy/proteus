package com.flipkart.android.proteus.parser;

import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.flipkart.android.proteus.R;
import com.flipkart.android.proteus.builder.ProtoLayoutBuilder;
import com.flipkart.android.proteus.processor.AttributeProcessor;
import com.flipkart.android.proteus.providers.Attribute;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.toolbox.Styles;
import com.flipkart.android.proteus.view.ProteusAndroidView;
import com.flipkart.android.proteus.view.ProteusView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * @author kushal.sharma
 */
public abstract class ProtoLayoutHandlerImpl<V extends View> implements ProtoLayoutHandler<V> {
    private static XmlResourceParser sParser = null;

    @Override
    public void onBeforeCreateView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {

    }

    @Override
    public ProteusView createView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        return new ProteusAndroidView(parent.getContext());
    }

    @Override
    public void onAfterCreateView(V view, ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        try {
            ViewGroup.LayoutParams layoutParams = generateDefaultLayoutParams(parent);
            view.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prepareAttributeHandlers() {

    }

    @Override
    public void addHandler(Attributes.Attribute key, AttributeProcessor<V> handler) {

    }

    @Override
    public boolean handleAttribute(V view, Attribute attribute) {
        switch (attribute.getKey()) {
            case "layout_width":
                if (attribute.getType().equals("string")) {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    if (layoutParams != null) {
                        if (attribute.getValue().string_value.equals("match_parent"))
                            layoutParams.width = -1;
                        if (attribute.getValue().string_value.equals("wrap_content"))
                            layoutParams.width = -2;
                        view.setLayoutParams(layoutParams);
                    }
                }
                if (attribute.getType().equals("number")) {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    if (layoutParams != null) {
                        layoutParams.width = attribute.getValue().number_value.intValue();
                        view.setLayoutParams(layoutParams);
                    }
                }
                break;
            case "layout_height":
                if (attribute.getType().equals("string")) {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    if (layoutParams != null) {
                        if (attribute.getValue().string_value.equals("match_parent"))
                            layoutParams.height = -1;
                        if (attribute.getValue().string_value.equals("wrap_content"))
                            layoutParams.height = -2;
                        view.setLayoutParams(layoutParams);
                    }
                }
                if (attribute.getType().equals("number")) {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    if (layoutParams != null) {
                        layoutParams.height = attribute.getValue().number_value.intValue();
                        view.setLayoutParams(layoutParams);
                    }
                }
                break;
            case "background":
                view.setBackgroundColor(Color.parseColor(attribute.getValue().string_value));
                break;
            case "paddingLeft":
                int paddingTop = view.getPaddingTop();
                view.setPadding(attribute.getValue().number_value.intValue(), paddingTop, 0, 0);
                break;
            case "paddingTop":
                int paddingLeft = view.getPaddingLeft();
                view.setPadding(paddingLeft, attribute.getValue().number_value.intValue(), 0, 0);
                break;

            default:
                Log.e("LayoutHandlerImp", "Cannot handle " + attribute.getKey());
        }
        return true;
    }

    @Override
    public boolean handleChildren(ProteusView view, Layout childLayout, ProtoLayoutBuilder layoutBuilder) {
        if (view instanceof ViewGroup) {
            ProteusView childView = layoutBuilder.build((ViewGroup) view, childLayout, null, 0, null);
            addView(view, childView);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addView(ProteusView parent, ProteusView view) {
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).addView((View) view);
            return true;
        }
        return false;
    }


    protected ViewGroup.LayoutParams generateDefaultLayoutParams(ViewGroup parent) throws IOException, XmlPullParserException {

        /**
         * This whole method is a hack! To generate layout params, since no other way exists.
         * Refer : http://stackoverflow.com/questions/7018267/generating-a-layoutparams-based-on-the-type-of-parent
         */
        if (null == sParser) {
            synchronized (Parser.class) {
                if (null == sParser) {
                    sParser = parent.getResources().getLayout(R.layout.layout_params_hack);
                    //noinspection StatementWithEmptyBody
                    while (sParser.nextToken() != XmlPullParser.START_TAG) {
                        // Skip everything until the view tag.
                    }
                }
            }
        }

        return parent.generateLayoutParams(sParser);
    }
}
