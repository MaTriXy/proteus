/*
 * Copyright 2016 Flipkart Internet Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flipkart.android.proteus.builder;

import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.Adapter;

import com.flipkart.android.proteus.EventType;
import com.flipkart.android.proteus.providers.Attributes;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.toolbox.Styles;
import com.flipkart.android.proteus.view.ProteusView;

import java.util.List;

/**
 * @author kiran.kumar
 */
public interface ProtoLayoutBuilderCallback {

    /**
     * called when the builder encounters an attributes key which is unhandled by its parser.
     *
     * @param attributes attributes that is being parsed
     * @param view      corresponding view for current attributes that is being parsed
     */
    void onUnknownAttribute(Attributes attributes, ProteusView view);

    /**
     * called when the builder encounters a view type which it cannot understand.
     */
    @Nullable
    ProteusView onUnknownViewType(String type, View parent, Layout layout, Data data, int index, Styles styles);

    Layout onLayoutRequired(String type, ProteusView parent);

    void onViewBuiltFromViewProvider(ProteusView view, View parent, String type, int index);

    /**
     * called when any click occurs on views
     *
     * @param view The view that triggered the event
     */
    View onEvent(ProteusView view, Attributes attributes, EventType eventType);

    PagerAdapter onPagerAdapterRequired(ProteusView parent, final List<ProteusView> children, Layout layout);

    Adapter onAdapterRequired(ProteusView parent, final List<ProteusView> children, Layout layout);

}
