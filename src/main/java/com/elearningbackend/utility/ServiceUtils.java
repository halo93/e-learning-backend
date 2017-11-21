package com.elearningbackend.utility;

import com.elearningbackend.customerrorcode.Errors;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ServiceUtils {
    public static Map<String, String> validateRequired(Object obj, String... fields){
        Map<String, String> errorsMap = new HashMap<>();
        for(String field : fields) {
            Field foundField = ReflectionUtils.findField(obj.getClass(), field);
            ReflectionUtils.makeAccessible(foundField);
            Object value = ReflectionUtils.getField(foundField, obj);
            if (value == null || (value instanceof String && ((String) value).isEmpty())){
                errorsMap.put(field, Errors.ERROR_FIELD_MISS.getMessage());
            }
        }
        return errorsMap;
    }
}
