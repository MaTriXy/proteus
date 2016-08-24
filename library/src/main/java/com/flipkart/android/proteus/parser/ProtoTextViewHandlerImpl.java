package com.flipkart.android.proteus.parser;

import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flipkart.android.proteus.builder.ProtoLayoutBuilder;
import com.flipkart.android.proteus.providers.Attribute;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.toolbox.Styles;
import com.flipkart.android.proteus.view.ProteusTextView;
import com.flipkart.android.proteus.view.ProteusView;

/**
 * @author kushal.sharma
 */
public class ProtoTextViewHandlerImpl extends ProtoLayoutHandlerImpl<TextView> {
    @Override
    public ProteusView createView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        return new ProteusTextView(parent.getContext());
    }

    @Override
    public boolean handleAttribute(TextView view, Attribute attribute) {
        super.handleAttribute(view, attribute);
        switch (attribute.getKey()) {
            case "text":
                view.setText(attribute.getValue().string_value);
                Log.e("LinearLayoutHandlerImpl", "handled " + attribute.getKey());
                break;
            case "textSize":
                view.setTextSize(attribute.getValue().number_value.floatValue());
                Log.e("LinearLayoutHandlerImpl", "handled " + attribute.getKey());
                break;
            case "textColor":
                view.setTextColor(Color.parseColor(attribute.getValue().string_value));
                Log.e("LinearLayoutHandlerImpl", "handled " + attribute.getKey());
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean handleChildren(ProteusView view, Layout childLayout, ProtoLayoutBuilder layoutBuilder) {
        return false;
    }

    @Override
    public boolean addView(ProteusView parent, ProteusView view) {
        return super.addView(parent, view);
    }
}
