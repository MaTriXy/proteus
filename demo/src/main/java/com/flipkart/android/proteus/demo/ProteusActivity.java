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
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;


public class ProteusActivity extends AppCompatActivity {
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init gson instance
        gson = new Gson();

        // Deserialize json data to objects. We will need this data for inflating proteus view.
        // This data should come from remote server if we wish to change layouts without app updates.
        // Styles styles = gson.fromJson(getJsonFromFile(R.raw.styles).getAsJsonObject(), Styles.class);
        Map<String, JsonObject> layoutProvider = getProviderFromFile(R.raw.layout_provider);
        JsonObject pageLayout = getJsonFromFile(R.raw.page_layout).getAsJsonObject();

        JsonObject data = getJsonFromFile(R.raw.data_init).getAsJsonObject();


        // Make a container layout with activity context and layoutParams for it.
        FrameLayout container = new FrameLayout(ProteusActivity.this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        // Get instance of proteusView from dataAndViewParsingLayoutBuilder

        // Add proteusView and layoutParams to container layout.

        // Set container layout to activity content view.
        setContentView(container);
    }

    private JsonElement getJsonFromFile(int resId) {
        InputStream inputStream = getResources().openRawResource(resId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return gson.fromJson(reader, JsonElement.class);
    }

    private Map<String, JsonObject> getProviderFromFile(int resId) {
        InputStream inputStream = getResources().openRawResource(resId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return gson.fromJson(reader, (new TypeToken<Map<String, JsonObject>>() {
        }).getType());
    }
}
