package com.flipkart.android.proteusproto.providers;

import com.flipkart.android.proteus.providers.AttributeKeyValue;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteusproto.models.ProtoLayout;

import java.util.Collection;

/**
 * @author kushal.sharma
 */

public class LayoutImpl implements Layout {
    private ProtoLayout.ProteusLayout proteusLayout;

    public LayoutImpl(ProtoLayout.ProteusLayout proteusLayout) {
        this.proteusLayout = proteusLayout;
    }

    @Override
    public String getType() {
        return proteusLayout.getDefaultInstanceForType().getViewGroup().getViewGroupTypeCase().name();
    }

    @Override
    public Collection<AttributeKeyValue> getAttributes() {
        return null;
    }

    @Override
    public Collection<Layout> getChildren() {
        return null;
    }
}
