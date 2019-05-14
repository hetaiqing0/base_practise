package reference;

/*
* Java中是值传递，仅仅是传递了引用的值，而没有传递引用本身
* 所以不能改变原引用指向的对象
* */

public class Executor {

    public static void main(String[] args) {
        User user = new User("leon", 25);
        changeName(user);
        System.out.println(user);
    }

    private static void changeName(User user) {
        user = new User("eagle", 26);
    }
}

