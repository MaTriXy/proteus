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

import com.flipkart.android.proteusproto.models.ProtoLayout;
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
                ProtoLayout.ProteusLayout proteusLayout = generateProteusLayout();
                byte[] bytes = getBytes(proteusLayout);
                ProtoLayout.ProteusLayout proteusLayoutFromBytes = null;
                try {
                    proteusLayoutFromBytes = getProteusLayout(bytes);
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @DebugLog
    private ProtoLayout.ProteusLayout getProteusLayout(byte[] bytes) throws InvalidProtocolBufferException {
        return ProtoLayout.ProteusLayout.parseFrom(bytes);
    }

    @DebugLog
    private byte[] getBytes(ProtoLayout.ProteusLayout proteusLayout) {
        return proteusLayout.toByteArray();
    }

    private ProtoLayout.ProteusLayout generateProteusLayout() {
        ProtoLayout.LayoutParams frameLayoutParams = ProtoLayout.LayoutParams.getDefaultInstance().newBuilderForType().setLayoutHeight(200).setLayoutWidth(200).build();
        ProtoLayout.View frameLayoutView = ProtoLayout.View.getDefaultInstance().newBuilderForType().setLayoutParams(frameLayoutParams).setBackgroundColor(0xFFFF0000).build();
        ProtoLayout.ViewGroup frameLayoutViewGroup = ProtoLayout.ViewGroup.getDefaultInstance().newBuilderForType().setView(frameLayoutView).build();
        ProtoLayout.FrameLayout frameLayout = ProtoLayout.FrameLayout.getDefaultInstance().newBuilderForType().setViewGroup(frameLayoutViewGroup).build();
        ProtoLayout.AnyViewGroup viewGroup = ProtoLayout.AnyViewGroup.getDefaultInstance().newBuilderForType().setFrameLayout(frameLayout).build();
        return ProtoLayout.ProteusLayout.getDefaultInstance().newBuilderForType().setViewGroup(viewGroup).build();
    }
}
