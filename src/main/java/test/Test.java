package test;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        // 开始执行
        long start = System.currentTimeMillis();
        /*=========================================================*/
        System.out.println("This is a new branch of master");
        /*=========================================================*/
        // 结束执行
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "毫秒");
    }
}
