package com.WangWick.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonPasswordExclusionPolicy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        if (fieldAttributes.getName().equals("password"))
            return true;
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
