package reflect.simple;

import java.lang.reflect.Field;

public class Executor {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        School schoolData = new School("清华大学", "北京");
        Student studentData = new Student("何太清", schoolData);

        Class<Student> studentClass = Student.class;
        Field studentNameField = studentClass.getDeclaredField("name");
        if (!studentNameField.isAccessible()) {
            studentNameField.setAccessible(true);
        }
        Object studentName = studentNameField.get(studentData);
        System.out.println(String.valueOf(studentName));

        Field studentSchoolField = studentClass.getDeclaredField("school");
        if (!studentSchoolField.isAccessible()) {
            studentSchoolField.setAccessible(true);
        }
        Object school = studentSchoolField.get(studentData);

        Class<School> schoolClass = School.class;
        Field schoolNameField = schoolClass.getDeclaredField("name");
        if (!schoolNameField.isAccessible()) {
            schoolNameField.setAccessible(true);
        }
        Object schoolName = schoolNameField.get(school);
        System.out.println(String.valueOf(schoolName));

        Field schoolAddressField = schoolClass.getDeclaredField("address");
        if (!schoolAddressField.isAccessible()) {
            schoolAddressField.setAccessible(true);
        }
        Object schoolAddress = schoolAddressField.get(school);
        System.out.println(String.valueOf(schoolAddress));
    }
}
