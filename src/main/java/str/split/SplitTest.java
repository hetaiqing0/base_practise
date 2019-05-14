package str.split;

public class SplitTest {
    public static void main(String[] args) throws IllegalAccessException {
        String str1 = "abc,def";
        String str2 = "abc";
        String[] strings1 = str1.split(",");
        for (String s : strings1) {
            System.out.println(s);
        }
        String[] strings2 = str2.split(",");
        for (String s : strings2) {
            System.out.println(s);
        }
    }
}
