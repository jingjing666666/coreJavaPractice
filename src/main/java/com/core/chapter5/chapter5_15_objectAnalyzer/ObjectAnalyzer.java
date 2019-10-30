package com.core.chapter5.chapter5_15_objectAnalyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * 这个程序展示分析对象
 * @Author: 020188
 * @Date: 2019/10/28
 */
public class ObjectAnalyzer {

    private ArrayList<Object> visited = new ArrayList<Object>();

    /**
     * 将对象转换为list所有变量的字符串表示
     * @param obj 一个对象
     * @return 返回一个String包括类的类名，所有变量名和值
     */
    public String toString(Object obj) {
        if (obj==null) return "null";
        if (visited.contains(obj)) return "...";
        visited.add(obj);
        Class clazz = obj.getClass();
        if (clazz.isArray()){
            String r = clazz.getComponentType()+"[]{";
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i>0) r+=",";
                Object val = Array.get(obj,i);
                if (clazz.getComponentType().isPrimitive()) r+=val;
                else r+=toString(val);
            }
            return r+"}";
        }
        String r = clazz.getName();
        //分析这个类和这个超类的所有变量
        do {
            r+="[";
            Field[] fields = clazz.getDeclaredFields();
            //为变量设置可访问标志
            AccessibleObject.setAccessible(fields,true);
            //获取变量名和变量的值
            for (Field field: fields) {
                if (!Modifier.isStatic(field.getModifiers())){
                    if (!r.endsWith("[")) r+=",";
                    r+=field.getName()+" = ";
                    try {
                        Class type = field.getType();
                        Object val = field.get(obj);
                        if (type.isPrimitive()) r+=val;
                        else r+=toString(val);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            r+="]";
            clazz=clazz.getSuperclass();
        }while (clazz!=null);
        return r;
    }
}
