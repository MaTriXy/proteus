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

package com.flipkart.android.proteus.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.FrameLayout;

import com.flipkart.android.proteus.EventType;
import com.flipkart.android.proteus.ImageLoaderCallback;
import com.flipkart.android.proteus.builder.LayoutBuilderImpl;
import com.flipkart.android.proteus.builder.ProtoLayoutBuilderCallback;
import com.flipkart.android.proteus.models.proto.Layout;
import com.flipkart.android.proteus.parser.ProtoLinearLayoutHandlerImpl;
import com.flipkart.android.proteus.parser.ProtoTextViewHandlerImpl;
import com.flipkart.android.proteus.providers.Attributes;
import com.flipkart.android.proteus.providers.Data;
import com.flipkart.android.proteus.providers.LayoutImpl;
import com.flipkart.android.proteus.toolbox.BitmapLoader;
import com.flipkart.android.proteus.toolbox.IdGeneratorImpl;
import com.flipkart.android.proteus.toolbox.Styles;
import com.flipkart.android.proteus.view.ProteusView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;


public class ProteusActivity extends AppCompatActivity {
    private Gson gson;

    /**
     * Simple implementation of BitmapLoader for loading images from url in background.
     */
    private BitmapLoader bitmapLoader = new BitmapLoader() {
        @Override
        public Future<Bitmap> getBitmap(String imageUrl, View view) {
            return null;
        }

        @Override
        public void getBitmap(String imageUrl, final ImageLoaderCallback callback, View view, JsonObject layout) {
            URL url;

            try {
                url = new URL(imageUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return;
            }

            new AsyncTask<URL, Integer, Bitmap>() {
                @Override
                protected Bitmap doInBackground(URL... params) {
                    try {
                        return BitmapFactory.decodeStream(params[0].openConnection().getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                protected void onPostExecute(Bitmap result) {
                    callback.onResponse(result);
                }
            }.execute(url);
        }
    };

    /**
     * Implementation of LayoutBuilderCallback. This is where we get callbacks from proteus regarding
     * errors and events.
     */
    private ProtoLayoutBuilderCallback callback = new ProtoLayoutBuilderCallback() {

        @Override
        public void onUnknownAttribute(Attributes attributes, ProteusView view) {

        }

        @Nullable
        @Override
        public ProteusView onUnknownViewType(String type, View parent, com.flipkart.android.proteus.providers.Layout layout, Data data, int index, Styles styles) {
            return null;
        }

        @Override
        public com.flipkart.android.proteus.providers.Layout onLayoutRequired(String type, ProteusView parent) {
            return null;
        }

        @Override
        public void onViewBuiltFromViewProvider(ProteusView view, View parent, String type, int index) {

        }

        @Override
        public View onEvent(ProteusView view, Attributes attributes, EventType eventType) {
            return null;
        }

        @Override
        public PagerAdapter onPagerAdapterRequired(ProteusView parent, List<ProteusView> children, com.flipkart.android.proteus.providers.Layout layout) {
            return null;
        }

        @Override
        public Adapter onAdapterRequired(ProteusView parent, List<ProteusView> children, com.flipkart.android.proteus.providers.Layout layout) {
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Layout.Value tvWidthValue2 = new Layout.Value.Builder().string_value("match_parent").build();
        Layout.Attributes tvWidth2 = new Layout.Attributes.Builder().key("layout_width").value(tvWidthValue2).type("string").build();
        Layout.Value tvHeightValue2 = new Layout.Value.Builder().string_value("wrap_content").build();
        Layout.Attributes tvHeight2 = new Layout.Attributes.Builder().key("layout_height").value(tvHeightValue2).type("string").build();
        Layout.Value textValue = new Layout.Value.Builder().string_value("Whats up!").build();
        Layout.Attributes tvText = new Layout.Attributes.Builder().key("text").value(textValue).type("string").build();
        Layout.Value textSizeValue = new Layout.Value.Builder().number_value(70.0).build();
        Layout.Attributes tvTextSize = new Layout.Attributes.Builder().key("textSize").value(textSizeValue).type("number").build();
        Layout.Value textColorValue = new Layout.Value.Builder().string_value("#B7BDE8").build();
        Layout.Attributes tvTextColor = new Layout.Attributes.Builder().key("textColor").value(textColorValue).type("string").build();
        Layout.Value textPaddingValue = new Layout.Value.Builder().number_value(120.0).build();
        Layout.Attributes tvTextPaddingLeft = new Layout.Attributes.Builder().key("paddingLeft").value(textPaddingValue).type("number").build();
        Layout.Attributes tvTextPaddingTop = new Layout.Attributes.Builder().key("paddingTop").value(textPaddingValue).type("number").build();


        List<Layout.Attributes> list = new ArrayList<>();
        list.add(tvHeight2);
        list.add(tvWidth2);
        list.add(tvText);
        list.add(tvTextSize);
        list.add(tvTextColor);
        list.add(tvTextPaddingLeft);
        list.add(tvTextPaddingTop);

        Layout.View childTextView = new Layout.View.Builder().type("TextView").attributes(list).build();

        List<Layout.View> viewList = new ArrayList<>();
        viewList.add(childTextView);

        Layout.Value linearLayoutWidthValue2 = new Layout.Value.Builder().number_value(1300.0).build();
        Layout.Attributes linearLayoutWidth2 = new Layout.Attributes.Builder().key("layout_width").value(linearLayoutWidthValue2).type("number").build();
        Layout.Value linearLayoutHeightValue2 = new Layout.Value.Builder().number_value(1000.0).build();
        Layout.Attributes linearLayoutHeight2 = new Layout.Attributes.Builder().key("layout_height").value(linearLayoutHeightValue2).type("number").build();
        Layout.Value linearLayoutOrientationValue2 = new Layout.Value.Builder().string_value("vertical").build();
        Layout.Attributes linearLayoutOrientation2 = new Layout.Attributes.Builder().key("orientation").value(linearLayoutOrientationValue2).type("string").build();
        Layout.Value linearLayoutBackgroundValue2 = new Layout.Value.Builder().string_value("#282D51").build();
        Layout.Attributes linearLayoutBackground2 = new Layout.Attributes.Builder().key("background").value(linearLayoutBackgroundValue2).type("string").build();

        List<Layout.Attributes> attributesList2 = new ArrayList<>();
        attributesList2.add(linearLayoutHeight2);
        attributesList2.add(linearLayoutWidth2);
        attributesList2.add(linearLayoutOrientation2);
        attributesList2.add(linearLayoutBackground2);

        Layout.View childLinearLayout2 = new Layout.View.Builder().type("LinearLayout").attributes(attributesList2).view(viewList).build();

        Layout.Value linearLayoutWidthValue1 = new Layout.Value.Builder().number_value(540.0).build();
        Layout.Attributes linearLayoutWidth1 = new Layout.Attributes.Builder().key("layout_width").value(linearLayoutWidthValue1).type("number").build();
        Layout.Value linearLayoutHeightValue1 = new Layout.Value.Builder().number_value(700.0).build();
        Layout.Attributes linearLayoutHeight1 = new Layout.Attributes.Builder().key("layout_height").value(linearLayoutHeightValue1).type("number").build();
        Layout.Value linearLayoutOrientationValue1 = new Layout.Value.Builder().string_value("vertical").build();
        Layout.Attributes linearLayoutOrientation1 = new Layout.Attributes.Builder().key("orientation").value(linearLayoutOrientationValue1).type("string").build();
        Layout.Value linearLayoutBackgroundValue1 = new Layout.Value.Builder().string_value("#99A4FF").build();
        Layout.Attributes linearLayoutBackground1 = new Layout.Attributes.Builder().key("background").value(linearLayoutBackgroundValue1).type("string").build();

        List<Layout.Attributes> attributesList1 = new ArrayList<>();
        attributesList1.add(linearLayoutHeight1);
        attributesList1.add(linearLayoutWidth1);
        attributesList1.add(linearLayoutOrientation1);
        attributesList1.add(linearLayoutBackground1);

        Layout.View childLinearLayout = new Layout.View.Builder().type("LinearLayout").attributes(attributesList1).build();

        List<Layout.View> childViewList = new ArrayList<>();
        childViewList.add(childLinearLayout);
        childViewList.add(childLinearLayout2);

        Layout.Value linearLayoutWidthValue = new Layout.Value.Builder().string_value("match_parent").build();
        Layout.Attributes linearLayoutWidth = new Layout.Attributes.Builder().key("layout_width").value(linearLayoutWidthValue).type("string").build();
        Layout.Value linearLayoutHeightValue = new Layout.Value.Builder().string_value("match_parent").build();
        Layout.Attributes linearLayoutHeight = new Layout.Attributes.Builder().key("layout_height").value(linearLayoutHeightValue).type("string").build();
        Layout.Value linearLayoutOrientationValue = new Layout.Value.Builder().string_value("vertical").build();
        Layout.Attributes linearLayoutOrientation = new Layout.Attributes.Builder().key("orientation").value(linearLayoutOrientationValue).type("string").build();
        Layout.Value linearLayoutBackgroundValue = new Layout.Value.Builder().string_value("#37418E").build();
        Layout.Attributes linearLayoutBackground = new Layout.Attributes.Builder().key("background").value(linearLayoutBackgroundValue).type("string").build();

        List<Layout.Attributes> attributesList = new ArrayList<>();
        attributesList.add(linearLayoutHeight);
        attributesList.add(linearLayoutWidth);
        attributesList.add(linearLayoutOrientation);
        attributesList.add(linearLayoutBackground);

        Layout.View linearLayout = new Layout.View.Builder().type("LinearLayout").attributes(attributesList).view(childViewList).build();

        List<Layout.View> viewList1 = new ArrayList<>();

        viewList1.add(linearLayout);

        Layout layout = new Layout.Builder().view(viewList1).build();

        // Init dataAndViewParsingLayoutBuilder and set layoutProvider, layoutBuilderCallback
        // and bitmapLoader we initialised before.
        LayoutBuilderImpl builder = new LayoutBuilderImpl(new IdGeneratorImpl());
        builder.setListener(callback);
        builder.setBitmapLoader(bitmapLoader);
        builder.registerHandler("LinearLayout", new ProtoLinearLayoutHandlerImpl());
        builder.registerHandler("TextView", new ProtoTextViewHandlerImpl());

        // Make a container layout with activity context and layoutParams for it.
        FrameLayout container = new FrameLayout(ProteusActivity.this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        // Get instance of proteusView from dataAndViewParsingLayoutBuilder
        ProteusView proteusView = builder.build(container, new LayoutImpl(layout.view.get(0)), null, 0, null);

        // Add proteusView and layoutParams to container layout.
        container.addView((ViewGroup) proteusView, layoutParams);

        // Set container layout to activity content view.
        setContentView(container);
    }
}
