/*
 * Copyright 2015 Tyler Davis
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.ngandroid.lib;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.ngandroid.lib.attacher.AttributeAttacher;

/**
 * Created by davityle on 1/12/15.
 */
public class NgAndroid {


    public static void setContentView(Activity activity, int resourceId) {
        new AttributeAttacher(activity, activity).setContentView(activity, resourceId);
    }

    public static void setContentView(Activity activity, int resourceId, Object model) {
        new AttributeAttacher(activity, model).setContentView(activity, resourceId);
    }

    public static View inflate(Activity activity, int resourceId, ViewGroup viewGroup, boolean attach){
        return new AttributeAttacher(activity, activity).inflate(resourceId, viewGroup, attach);
    }

    public static View inflate(Activity activity, int resourceId, ViewGroup viewGroup){
        return new AttributeAttacher(activity, activity).inflate(resourceId, viewGroup, false);
    }

    public static View inflate(Activity activity, int resourceId, ViewGroup viewGroup, boolean attach, Object model){
        return new AttributeAttacher(activity, model).inflate(resourceId, viewGroup, attach);
    }
}
