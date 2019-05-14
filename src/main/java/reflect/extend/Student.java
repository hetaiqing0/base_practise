package reflect.extend;

import java.util.List;

public class Student extends People {
    private School school;
    private List<Course> courses;

    public Student() {
    }

    public Student(School school, List<Course> courses) {
        this.school = school;
        this.courses = courses;
    }

    public Student(String name, int age, String[] parents, School school, List<Course> courses) {
        super(name, age, parents);
        this.school = school;
        this.courses = courses;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
