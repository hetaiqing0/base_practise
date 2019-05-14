package reflect.extend;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class Executor3 {
    public static void main(String[] args) {

        List<Course> courses = new ArrayList<>();
        String[] parenets = {"father", "mother"};
        School school = new School("北京");
        Course c1 = new Course("语文");
        Course c2 = new Course("数学");
        Course c3 = new Course("英语");
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);

        Student student = new Student("何太清", 25, parenets, school, courses);

        // 开始执行
        long start = System.currentTimeMillis();
        /*=========================================================*/
        for (int i = 0; i < 10000000; i++) {
            getValuesByTemplate(student, "courses.name");
        }
        String name = getValuesByTemplate(student, "courses.name");
        System.out.println(name);
        /*=========================================================*/
        // 结束执行
        long end = System.currentTimeMillis();

        System.out.println("共用时:" + (end - start) + "毫秒");
    }

    /**
     * 根据模板字符串找到对应属性值
     * 暂只提供POJO及参数化类型为POJO的List、Array的解析
     *
     * @param provider 提供值的对象，不接受null
     * @param template 模板，形如：a a.b a.b.c.d 可以为对象属性，也可以为Array、List中的对象属性
     * @return 如果该属性不存在，返回null。该属性的值不存在，返回空串
     */
    private static String getValuesByTemplate(Object provider, String template) {
        String[] fieldNames = template.split("\\.");
        Class<?> clazz = provider.getClass();

        // 存放对象，由于对象可能是List或数组，故用List存储
        List<Object> objs = new ArrayList<>();
        List<Object> objsTemp = new ArrayList<>();

        objs.add(provider);

        // 用于记录最后的Field
        Field field;
        for (String fieldName : fieldNames) {
            field = getFieldByName(clazz, fieldName);
            // 没找到指定属性
            if (field == null) {
                return null;
            }
            // 更新objs，对象列表->属性列表
            for (Object obj : objs) {
                // 实际上这个异常是不可能产生的
                // field在getFieldByName中已被设置为可访问
                try {
                    objsTemp.addAll(getObjs(obj, field));
                } catch (IllegalAccessException e) {
                    return null;
                }
            }
            objs = new ArrayList<>(objsTemp);
            objsTemp.clear();

            // 更新clazz
            for (Object obj : objs) {
                if (obj != null) {
                    clazz = obj.getClass();
                }
            }
            // 移除objs中的null
            while (objs.contains(null)) {
                objs.remove(null);
            }
            // 如果移除null后，objs中无值，证明所有属性都无值
            if (objs.size() == 0) {
                return "";
            }
        }

        for (Object obj : objs) {
            objsTemp.add(String.valueOf(obj));
        }
        // 移除空元素
        while (objsTemp.contains("")) {
            objsTemp.remove("");
        }
        return StringUtils.join(objsTemp, ";");
    }

    /**
     * 从对象获取指定属性列表
     * @param provider 实例对象
     * @param field Field
     * @return
     */
    private static List<Object> getObjs(Object provider, Field field) throws IllegalAccessException {
        List<Object> objs = new ArrayList<>();
        Class<?> type = field.getType();
        if (type.isArray()) {
            objs = getObjectsFromArray(provider, field);
        } else if (type == List.class) {
            objs = getObjectsFromList(provider, field);
        } else {
            Object result = field.get(provider);
            objs.add(result);
        }
        return objs;
    }


    /**
     * 从Array中取到指定属性的值的列表
     * @param provider 实例对象
     * @param field Field
     * @return
     */
    private static List<Object> getObjectsFromArray(Object provider, Field field) throws IllegalAccessException {
        List<Object> objs = new ArrayList<>();
        // 获取到的Array
        Object array = field.get(provider);
        for (int i = 0; i < Array.getLength(array); i++) {
            objs.add(Array.get(array, i));
        }
        return objs;
    }

    /**
     * 从List中取到指定属性的值的列表
     * @param provider 实例对象
     * @param field Field
     * @return
     */
    private static List<Object> getObjectsFromList(Object provider, Field field) throws IllegalAccessException {
        List<Object> objs = new ArrayList<>();
        List list = (List) field.get(provider);
        objs.addAll(list);
        return objs;
    }

    /**
     * 根据Class和属性名找到Field
     *
     * @param clazz
     * @param fieldName
     * @return 若该属性不存在，返回null
     */
    private static Field getFieldByName(Class<?> clazz, String fieldName) {
        Map<String, Field> fieldMap = new HashMap<>();
        while (clazz != null) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                fieldMap.put(declaredField.getName(), declaredField);
            }
            clazz = clazz.getSuperclass();
        }
        return fieldMap.get(fieldName);
    }
}
