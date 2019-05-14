package reflect.extend;

public class People {
    private String name;
    private int age;
    private String[] parents;

    public People() {
    }

    public People(String name, int age, String[] parents) {
        this.name = name;
        this.age = age;
        this.parents = parents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getParents() {
        return parents;
    }

    public void setParents(String[] parents) {
        this.parents = parents;
    }
}
