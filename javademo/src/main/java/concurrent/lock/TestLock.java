package concurrent.lock;

import java.util.concurrent.locks.Lock;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestLock {
    private Lock lock;
    public static void main(String[] args) {
       boolean dsa=true;
       int a=2;
       if( dsa||++a==4 ){
           System.out.println(a);
           a++;
       }
        System.out.println(a);

    }

    class lockTest{
        public lockTest(){

        }

    }

}
