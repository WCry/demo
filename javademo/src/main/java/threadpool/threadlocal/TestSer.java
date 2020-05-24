package threadpool.threadlocal;

import java.io.Serializable;

public class TestSer extends TestAbstract implements Serializable {

    transient ThreadLocal<Integer> threadLocal=new ThreadLocal();
    public void setDsad(Integer ds){
        threadLocal.set(ds);
    }
    public Integer getDsad(){
        return threadLocal.get();
    }
}
