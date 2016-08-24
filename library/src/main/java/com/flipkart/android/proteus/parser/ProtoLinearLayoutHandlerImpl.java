package com.flipkart.android.proteus.parser;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flipkart.android.proteus.builder.ProtoLayoutBuilder;
import com.flipkart.android.proteus.providers.Attribute;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.toolbox.Styles;
import com.flipkart.android.proteus.view.ProteusLinearLayout;
import com.flipkart.android.proteus.view.ProteusView;

/**
 * @author kushal.sharma
 */
public class ProtoLinearLayoutHandlerImpl extends ProtoLayoutHandlerImpl<LinearLayout> {
    @Override
    public ProteusView createView(ViewGroup parent, Layout layout, Data data, Styles styles, int index) {
        return new ProteusLinearLayout(parent.getContext());
    }

    @Override
    public boolean handleAttribute(LinearLayout view, Attribute attribute) {
        super.handleAttribute(view, attribute);
        switch (attribute.getKey()) {
            case "orientation":
                if (attribute.getValue().string_value.equals("vertical"))
                    view.setOrientation(LinearLayout.VERTICAL);
                if (attribute.getValue().string_value.equals("horizontal"))
                    view.setOrientation(LinearLayout.HORIZONTAL);
                Log.e("LinearLayoutHandlerImpl", "handled " + attribute.getKey());
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean handleChildren(ProteusView view, Layout childLayout, ProtoLayoutBuilder layoutBuilder) {
        return super.handleChildren(view, childLayout, layoutBuilder);
    }

    @Override
    public boolean addView(ProteusView parent, ProteusView view) {
        return super.addView(parent, view);
    }
}
