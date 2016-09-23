package com.flipkart.android.proteusproto.builder;

import android.view.ViewGroup;

import com.flipkart.android.proteus.builder.LayoutBuilder;
import com.flipkart.android.proteus.builder.LayoutBuilderCallback;
import com.flipkart.android.proteus.parser.LayoutHandler;
import com.flipkart.android.proteus.providers.AttributeKeyValue;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.providers.Styles;
import com.flipkart.android.proteus.toolbox.BitmapLoader;
import com.flipkart.android.proteus.toolbox.IdGenerator;
import com.flipkart.android.proteus.view.ProteusView;

/**
 * @author kushal.sharma
 */
public class LayoutBuilderImpl implements LayoutBuilder {
    @Override
    public void registerHandler(String type, LayoutHandler handler) {
    }

    @Override
    public void unregisterHandler(String type) {

    }

    @Override
    public LayoutHandler getHandler(String type) {
        return null;
    }

    @Override
    public boolean handleAttribute(LayoutHandler handler, ProteusView view, AttributeKeyValue attributeKeyValue) {
        return false;
    }

    @Override
    public ProteusView build(ViewGroup parent, Layout layout, Data data, int index, Styles styles) {
        return null;
    }

    @Override
    public int getUniqueViewId(String id) {
        return 0;
    }

    @Override
    public IdGenerator getIdGenerator() {
        return null;
    }

    @Override
    public LayoutBuilderCallback getListener() {
        return null;
    }

    @Override
    public void setListener(LayoutBuilderCallback listener) {

    }

    @Override
    public BitmapLoader getNetworkDrawableHelper() {
        return null;
    }

    @Override
    public void setBitmapLoader(BitmapLoader bitmapLoader) {

    }

    @Override
    public boolean isSynchronousRendering() {
        return false;
    }

    @Override
    public void setSynchronousRendering(boolean isSynchronousRendering) {

    }
}
