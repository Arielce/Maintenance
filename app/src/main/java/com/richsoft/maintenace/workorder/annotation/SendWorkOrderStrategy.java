package com.richsoft.maintenace.workorder.annotation;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * 作者：e430 on 2016/9/26 21:32
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class SendWorkOrderStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        Collection<Annotation> annotations = f.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == SendWorkOrder.class) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
