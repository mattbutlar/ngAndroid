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

package com.github.davityle.ngprocessor.attrcompiler.sources;


import java.util.List;

import javax.lang.model.type.TypeMirror;

/**
 * Created by davityle on 1/24/15.
 */
public class StaticSource extends Source<StaticSource> {
    private final String source;

    public StaticSource(String source, TypeMirror typeMirror) {
        super(typeMirror);
        this.source = source;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override public void getModelSource(List<ModelSource> models) {}
    @Override public void getMethodSource(List<MethodSource> methods) {}

    @Override
    public boolean isVoid() {
        return false;
    }

    @Override
    protected StaticSource cp(TypeMirror typeMirror) throws IllegalArgumentException {
        return new StaticSource(source, typeMirror);
    }
}
