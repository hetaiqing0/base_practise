package reflect.extend;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class Executor {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
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

        *//*===========================使用反射求值=========================*//*
        String[] strings = {"name", "age", "school.address", "courses.name"};

        String[] str = getSingleName(student, "courses.name");
        System.out.println(str);
*/
    }

    private static Field getField(Class<?> clazz, String fieldName) {
        Map<String, Field> fields = new HashMap<String, Field>();
        while (clazz != null) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                fields.put(declaredField.getName(), declaredField);
            }
            clazz = clazz.getSuperclass();
        }
        return fields.get(fieldName);
    }

    /**
     *
     * @param clazz 不接受null
     * @param fieldName
     * @return
     */
    private static Field getFieldEagle(Class<?> clazz, String fieldName) {
        Exception exception = new NoSuchFieldException();
        Field resultField = null;

        while ((clazz != null) && (exception instanceof NoSuchFieldException)) {
            try {
                exception = null;
                resultField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                exception = e;
                clazz = clazz.getSuperclass();
            }
        }
        return resultField;
    }

/*
    private static String getSingleName(Student student, String str) throws IllegalAccessException {
        String[] fieldNames = str.split("\\.");

        Class<?> clazz = Student.class;
        Field field;
        Object obj = student;

        for (String fieldName : fieldNames) {
            field = getField(clazz, fieldName);
            obj = field.get(obj);
            clazz = field.getType();
        }
        return obj.toString();
    }
    */


    // 此方法的局限性为List只能为倒数第2个值
    private static String[] getSingleName(Student student, String str) throws IllegalAccessException, InstantiationException {
        String[] result;
        boolean listFlag = false;
        String[] fieldNames = str.split("\\.");

        Class<?> clazz = Student.class;
        Field field = null;
        Object obj = student;

        for (String fieldName : fieldNames) {
            field = getField(clazz, fieldName);
            obj = field.get(obj);
            if (obj == null) {
                return null;
            }
            clazz = field.getType();
            if (clazz == List.class) {
                listFlag = true;
                break;
            }
        }

        if (listFlag) {
            Type type = field.getGenericType();
            if (type instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) type;
                Class<?> clazz1 = (Class<?>) pt.getActualTypeArguments()[0];

                if (clazz1 == Course.class) {
                    System.out.println("hello world");
                }
            }
        }

        result = new String[1];
        result[0] = obj.toString();
        return result;
    }

}
