package jvmTest;

/**
 * @author zhangxuepei
 */
public class TestStack {
    private static int count = 0;

    public static void recursion(long a, long b, long c) {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count++;
        recursion(a, b, c);
    }
    public static void main(String args[]) {
        try {
            //通过设置 -Xss128k 进行设置线程堆栈大小
            //堆栈大小影响线程递归调用的深度，默认使用1M
            //1M的调用深度9千多调用深度
            recursion(0L, 0L, 0L);
        } catch (Throwable e) {
            System.out.println("deep of calling = " + count);
           // e.printStackTrace();
        }
    }
}
