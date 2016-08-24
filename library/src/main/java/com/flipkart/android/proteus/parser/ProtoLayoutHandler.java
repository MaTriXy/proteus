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

package com.flipkart.android.proteus.parser;

import android.view.View;
import android.view.ViewGroup;

import com.flipkart.android.proteus.builder.ProtoLayoutBuilder;
import com.flipkart.android.proteus.processor.AttributeProcessor;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.toolbox.Styles;
import com.flipkart.android.proteus.view.ProteusView;

/**
 * @author kiran.kumar
 */
public interface ProtoLayoutHandler<V extends View> {

    void onBeforeCreateView(ViewGroup parent, Layout layout, Data data, Styles styles, int index);

    ProteusView createView(ViewGroup parent, Layout layout, Data data, Styles styles, int index);

    void onAfterCreateView(V view, ViewGroup parent, Layout layout, Data data, Styles styles, int index);

    void prepareAttributeHandlers();

    void addHandler(com.flipkart.android.proteus.parser.Attributes.Attribute key, AttributeProcessor<V> handler);

    boolean handleAttribute(V view, com.flipkart.android.proteus.providers.Attribute attribute);

    boolean handleChildren(ProteusView view, Layout childLayout, ProtoLayoutBuilder layoutBuilder);

    boolean addView(ProteusView parent, ProteusView view);
}
