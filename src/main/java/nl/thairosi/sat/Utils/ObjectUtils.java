package nl.thairosi.sat.Utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * The ObjectUtils class will be instantiated for using Object related methods
 */
public class ObjectUtils {

    /**
     * This getFieldNamesAndValues creates a Map of all object attributes and its given values
     *
     * @param object is the Object where the attributes and values will be copied from
     * @return a map of all attributes with their values
     */
    public static Map<String, Object> getFieldNamesAndValues(Object object) {
        Class c = object.getClass();
        Map<String, Object> map = new HashMap<>();
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            fields[i].setAccessible(true);
            Object value = null;
            try {
                value = fields[i].get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(name, value);
        }
        return map;
    }

}
