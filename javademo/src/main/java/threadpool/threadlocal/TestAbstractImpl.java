package threadpool.threadlocal;

import java.io.Serializable;

public class TestAbstractImpl extends TestAbstract implements Serializable {

    transient ThreadLocal<Integer> threadLocal=new ThreadLocal();
    public void setInterValue(Integer ds){
        threadLocal.set(ds);
    }
    public Integer getInterValue(){
        return threadLocal.get();
    }
}
