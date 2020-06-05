package com.example.consulfeignapi;

/**
 * @author zhangxuepei
 * @since 3.0
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public interface NamedEnum extends Serializable {
    String getName();

    Map<Class, Map> ENUM_MAP = new HashMap<>();

    static <E extends Enum & NamedEnum> E getByName(String name, Class<E> clazz)  {
        //Class<E> clazz = E.class;
        Map enumMap = ENUM_MAP.get(clazz);
        if(null == enumMap) {
            E[] enums = clazz.getEnumConstants();
            enumMap = new HashMap<String, E>();
            for(E current : enums) {
                enumMap.put(current.getName(), current);
            }
        }
        E result =  (E) enumMap.get(name);
        if(result != null) {
            return result;
        } else {
            throw new RuntimeException("No element matches " + name);
        }
    }
}
