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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.flipkart.android.proteus.builder.LayoutBuilder;
import com.flipkart.android.proteus.providers.Layout;
import com.flipkart.android.proteus.view.ProteusView;
import com.flipkart.android.proteusproto.builder.LayoutBuilderImpl;
import com.flipkart.android.proteusproto.models.ProteusLayout;
import com.flipkart.android.proteusproto.parser.FrameLayoutHandler;
import com.flipkart.android.proteusproto.providers.LayoutImpl;
import com.google.protobuf.InvalidProtocolBufferException;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonTestProto = (Button) findViewById(R.id.test_proto);
        buttonTestProto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProteusLayout.AnyViewOrViewGroup proteusLayout = generateProteusLayout();
                byte[] bytes = getBytes(proteusLayout);
                makeProteusProtoLayout(bytes);
            }
        });
    }

    @DebugLog
    private void makeProteusProtoLayout(byte[] bytes) {
        ProteusLayout.AnyViewOrViewGroup proteusLayoutFromBytes = null;
        try {
            proteusLayoutFromBytes = getProteusLayout(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        // get the containerView
        FrameLayout containerView = (FrameLayout) findViewById(R.id.container);
        // get the layout from bytes and make layoutImpl
        assert proteusLayoutFromBytes != null;
        Layout layout = new LayoutImpl(proteusLayoutFromBytes);
        LayoutBuilder layoutBuilder = new LayoutBuilderImpl();
        //TODO come back and register handlers when done with implementations
        layoutBuilder.registerHandler("VIEW", null);
        layoutBuilder.registerHandler("VIEWGROUP", null);
        layoutBuilder.registerHandler("FRAMELAYOUT", new FrameLayoutHandler());
        layoutBuilder.registerHandler("LINEARLAYOUT", null);
        layoutBuilder.registerHandler("TEXTVIEW", null);
        layoutBuilder.registerHandler("BUTTON", null);
        // get the proteusView from layout
        ProteusView proteusView = layoutBuilder.build(containerView, layout, null, 0, null);
        containerView.addView((View) proteusView);
    }

    private ProteusLayout.AnyViewOrViewGroup getProteusLayout(byte[] bytes) throws InvalidProtocolBufferException {
        return ProteusLayout.AnyViewOrViewGroup.parseFrom(bytes);
    }

    private byte[] getBytes(ProteusLayout.AnyViewOrViewGroup proteusLayout) {
        return proteusLayout.toByteArray();
    }

    private ProteusLayout.AnyViewOrViewGroup generateProteusLayout() {
        ProteusLayout.LayoutParams frameLayoutParams = ProteusLayout.LayoutParams.getDefaultInstance().newBuilderForType().setLayoutHeight(150).setLayoutWidth(150).build();
        ProteusLayout.View frameLayoutView = ProteusLayout.View.getDefaultInstance().newBuilderForType().setLayoutParams(frameLayoutParams).setBackgroundColor(0xFFFF0000).build();
        ProteusLayout.ViewGroup frameLayoutViewGroup = ProteusLayout.ViewGroup.getDefaultInstance().newBuilderForType().setView(frameLayoutView).build();
        ProteusLayout.FrameLayout frameLayout = ProteusLayout.FrameLayout.getDefaultInstance().newBuilderForType().setViewGroup(frameLayoutViewGroup).build();
        return ProteusLayout.AnyViewOrViewGroup.getDefaultInstance().newBuilderForType().setFrameLayout(frameLayout).build();
    }
}
