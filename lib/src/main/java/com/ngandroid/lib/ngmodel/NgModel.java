package com.ngandroid.lib.ngmodel;

import android.view.View;
import android.widget.TextView;

import com.ngandroid.lib.interpreter.Token;
import com.ngandroid.lib.interpreter.TokenType;
import com.ngandroid.lib.ng.ModelBuilder;
import com.ngandroid.lib.ng.ModelBuilderMap;
import com.ngandroid.lib.ng.ModelMethod;
import com.ngandroid.lib.ng.NgAttribute;
import com.ngandroid.lib.utils.TypeUtils;

import java.util.List;

/**
 * Created by davityle on 1/17/15.
 */
public class NgModel implements NgAttribute {
    private static NgModel ourInstance = new NgModel();

    public static NgModel getInstance() {
        return ourInstance;
    }

    private NgModel() {}

    public void typeCheck(Token[] tokens){
        TypeUtils.strictTypeCheck(tokens, TokenType.MODEL_NAME, TokenType.PERIOD, TokenType.MODEL_FIELD, TokenType.EOF);
    }

    public void attach(final Token[] tokens, Object mModel, ModelBuilderMap builders, View bindView){
        typeCheck(tokens);
        String modelName = tokens[0].getScript();
        String fieldName = tokens[2].getScript();
        ModelBuilder builder = builders.get(modelName);
        bindModelView(fieldName, bindView, builder, mModel);
    }

    public void bindModelView(String fieldName, View view, ModelBuilder builder, Object mModel) {
        if(view instanceof  TextView){
            bindModelToTextView(fieldName, (TextView)view, builder, mModel);
        }
    }

    public void bindModelToTextView(String fieldName, final TextView textView, ModelBuilder builder, Object mModel){
        final String fieldNamelower = fieldName.toLowerCase();
        String defaultText =  textView.getText().toString();
        builder.setField(fieldNamelower, defaultText);

        int methodType = builder.getMethodType(fieldNamelower);
        final SetTextWhenChangedListener setTextWhenChangedListener = new SetTextWhenChangedListener(fieldNamelower, builder.getMethodInvoker(), mModel, methodType);
        textView.addTextChangedListener(setTextWhenChangedListener);
        // TODO clean this up
        ModelMethod method = new ModelMethod(){
            @Override
            public Object invoke(String fieldName, Object... args) {
                String value = String.valueOf(args[0]);
                if(!value.equals(textView.getText().toString())) {
                    textView.removeTextChangedListener(setTextWhenChangedListener);
                    textView.setText(value);
                    textView.addTextChangedListener(setTextWhenChangedListener);
                }
                return null;
            }
        };
        String methodName = "set" + fieldName.toLowerCase();
        List<ModelMethod> methods = builder.getMethods(methodName);
        methods.add(method);
    }









}
