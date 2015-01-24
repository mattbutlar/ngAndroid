package com.ngandroid.lib.utils;

import com.ngandroid.lib.interpreter.Token;
import com.ngandroid.lib.interpreter.TokenType;

/**
 * Created by davityle on 1/13/15.
 */
public class TypeUtils {
    public static final int INTEGER = 0, LONG = 1, STRING = 2, DOUBLE = 3, FLOAT = 4, SHORT = 5, BYTE = 6, BOOLEAN = 7, OBJECT = 8;

    public static int getType(Class<?> clzz){
        if(int.class.equals(clzz) || Integer.class.equals(clzz))
            return INTEGER;
        if(long.class.equals(clzz) || Long.class.equals(clzz))
            return LONG;
        if(String.class.equals(clzz))
            return STRING;
        if(double.class.equals(clzz) || Double.class.equals(clzz))
            return DOUBLE;
        if(float.class.equals(clzz) || Float.class.equals(clzz))
            return FLOAT;
        if(short.class.equals(clzz) || Short.class.equals(clzz))
            return SHORT;
        if(byte.class.equals(clzz) || Byte.class.equals(clzz))
            return BYTE;
        if(boolean.class.equals(clzz) || Boolean.class.equals(clzz))
            return BOOLEAN;
        return OBJECT;
    }

    public static Object fromStringEmptyStringIfEmpty(int type, String value) throws Exception {
        if(!value.trim().isEmpty()) {
            switch (type) {
                case INTEGER:
                    return Integer.parseInt(value);
                case LONG:
                    return Long.parseLong(value);
                case DOUBLE:
                    return Double.parseDouble(value);
                case FLOAT:
                    return Float.parseFloat(value);
                case SHORT:
                    return Short.parseShort(value);
                case BYTE:
                    return Byte.parseByte(value);
                case BOOLEAN:
                    return Boolean.parseBoolean(value);
                case STRING:
                case OBJECT:
                    // TODO what to do here?
                default:
                    return value;
            }
        }
        return value;
    }

    public static Object fromString(int type, String value) throws Exception {
        if(!value.trim().isEmpty()) {
            switch (type) {
                case INTEGER:
                    return Integer.parseInt(value);
                case LONG:
                    return Long.parseLong(value);
                case DOUBLE:
                    return Double.parseDouble(value);
                case FLOAT:
                    return Float.parseFloat(value);
                case SHORT:
                    return Short.parseShort(value);
                case BYTE:
                    return Byte.parseByte(value);
                case BOOLEAN:
                    return Boolean.parseBoolean(value);
                case STRING:
                case OBJECT:
                    // TODO what to do here with object?
                default:
                    return value;
            }
        }else{
            switch (type) {
                case INTEGER:
                    return 0;
                case LONG:
                    return 0l;
                case DOUBLE:
                    return 0d;
                case FLOAT:
                    return 0f;
                case SHORT:
                    return (short)0;
                case BYTE:
                    return (byte)0;
                case BOOLEAN:
                    return false;
                case STRING:
                case OBJECT:
                    // TODO what to do here with object?
                default:
                    return value;
            }
        }
    }

    private static void assertEquals(Object actual, Object expected){
        if(!actual.equals(expected)){
            // TODO error
            throw new RuntimeException("Invalid syntax. Expected '" + expected + "' actual was '" + actual + '\'');
        }
    }

    public static void strictTypeCheck(Token[] tokens, TokenType ... types){
        if(tokens.length != types.length){
            // TODO error
            throw new RuntimeException("Invalid syntax. Expected " + types.length + " tokens; actual was " + tokens.length);
        }
        for(int index = 0; index < tokens.length; index++)
            assertEquals(tokens[index].getTokenType(), types[index]);
    }

    public static void looseTypeCheck(Token[] tokens, TokenType ... types){
        int foundCount = 0;
        for(int i = 0; i < types.length; i++){
            for(int t = i; t < tokens.length; t++){
                if(tokens[t].getTokenType() == types[i]){
                    foundCount++;
                    break;
                }
            }
        }
        assertEquals(foundCount, types.length);
    }

    public static void startsWith(Token[] tokens, TokenType type){
        if(tokens.length == 0)
            throw new RuntimeException("Empty string in attribute. Expecting " + type);
        assertEquals(tokens[0].getTokenType(), type);
    }

    public static void endsWith(Token[] tokens, TokenType type){
        if(tokens.length < 2)
            throw new RuntimeException("Empty string in attribute. Expecting " + type);
        // assuming last token is EOF
        assertEquals(tokens[tokens.length - 2].getTokenType(), type);
    }


}
