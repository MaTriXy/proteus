package com.flipkart.android.proteusproto.builder;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.flipkart.android.proteus.builder.LayoutBuilder;
import com.flipkart.android.proteus.builder.LayoutBuilderCallback;
import com.flipkart.android.proteus.parser.LayoutHandler;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.providers.Styles;
import com.flipkart.android.proteus.toolbox.BitmapLoader;
import com.flipkart.android.proteus.toolbox.IdGenerator;
import com.flipkart.android.proteus.view.ProteusView;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author kushal.sharma
 */
public class LayoutBuilderImpl implements LayoutBuilder {
    private HashMap<String, LayoutHandler> layoutHandlers = new HashMap<>();

    @Override
    public void registerHandler(String type, LayoutHandler handler) {
        //TODO add implementation for handler.prepareAttributeHandlers
        //handler.prepareAttributeHandlers();
        layoutHandlers.put(type, handler);
    }

    @Override
    public void unregisterHandler(String type) {
        layoutHandlers.remove(type);
    }

    @Override
    public LayoutHandler getHandler(String type) {
        return layoutHandlers.get(type);
    }

    @Override
    public ProteusView build(ViewGroup parent, Layout layout, Data data, int index, Styles styles) {
        ProteusView view;

        //TODO add implementation for layout.getType
        String type = layout.getType();
        if (null == type) {
            throw new IllegalArgumentException("'Type' missing in layout: " + layout.toString());
        }

        LayoutHandler handler = layoutHandlers.get(type);
        if (null == handler) {
            Log.e("LayoutBuilderImpl", "No handler for layout type " + type + ". Did you forget to register the handler?");
        }

        assert handler != null;
        view = handler.createView(parent, layout, data, styles, index);

        //TODO add implementation for handler.handleAttributes
        handler.handleAttributes((View) view, layout.getAnyViewOrViewGroup());

        Collection<Layout> layoutChildren = layout.getChildren();
        assert layoutChildren != null;
        if (layoutChildren.size() > 0) {
            for (Layout layoutChild : layoutChildren) {
                //TODO add implementation for handler.handleChildLayout
                handler.handleChildLayout(view, layoutChild);
            }
        }
        return view;
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
