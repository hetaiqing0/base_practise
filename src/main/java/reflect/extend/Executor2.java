package reflect.extend;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Executor2 {
    public static void main(String[] args) throws IllegalAccessException {
        /*
        List<Course> courses = new ArrayList<Course>();
        Course c1 = new Course("语文");
        Course c2 = new Course("数学");
        Course c3 = new Course("英语");
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        School school = new School("北京");

        Student student = new Student("何太清", 25, school, courses);
        Map<String, Field> fields = getFields(Student.class);
        Object courses1 = getFieldValue(fields, student, "courses");
        System.out.println("hello world");
*/

    }

    /**
     * 找到指定类及其父类的所有Field，存入Map中
     * @param clazz 指定类
     * @return
     */
    private static Map<String, Field> getFields(Class clazz) {
        Map<String, Field> fieldMap = new HashMap<>();
        while (clazz != null) {
            Field[] declaredFields = clazz.getDeclaredFields();
            if (declaredFields.length > 1) {
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    fieldMap.put(declaredField.getName(), declaredField);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return fieldMap;
    }

    /**
     * 从提供的对象中取出对应属性的值，并转换成所需的数据类型
     * @param fields 属性Map
     * @param provider 提供对象
     * @param fieldName 属性名
     * @param clazz 返回的数据类型
     * @param <T> 返回的数据类型
     * @return
     * @throws IllegalAccessException
     */
    private static <T> T getFieldValue(Map<String, Field> fields, Object provider, String fieldName, Class<T> clazz) throws IllegalAccessException {
        Field field = fields.get(fieldName);
        Object result = field.get(provider);
        return clazz.cast(result);
    }

    /**
     * 从提供的对象中取出对应属性的值，并转换成所需的数据类型
     * @param fields 属性Map
     * @param provider 提供对象
     * @param fieldName 属性名
     * @return
     * @throws IllegalAccessException
     */
    private static Object getFieldValue(Map<String, Field> fields, Object provider, String fieldName) throws IllegalAccessException {
        Field field = fields.get(fieldName);
        Object result = field.get(provider);
        return result;
    }

    private static <T> T getObjectFromString(Map<String, Field> fields) {
        return null;
    }

}
