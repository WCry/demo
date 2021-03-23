package automicclass;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 关于compareAndSet和weakCompareAndSet(表示可能会成功)
 *  有虚拟机的实现保证CAS的成功
 *  能够解决ABA问题，主要是因为在CAS的过程中增加了一个自增的版本信息
 */
public class TestAtomicStampedReference {
    public static void main(String[] args) {
        String str1 = "aaa";
        String str2 = "bbb";
        AtomicStampedReference<String> reference = new AtomicStampedReference<>(str1, 1);
        reference.compareAndSet(str1,str2,reference.getStamp(),reference.getStamp()+1);
        System.out.println("reference.getReference() = " + reference.getReference());

        boolean b = reference.attemptStamp(str2, reference.getStamp() + 1);
        System.out.println("b: "+b);
        System.out.println("reference.getStamp() = "+reference.getStamp());
        boolean c = reference.weakCompareAndSet(str2,"ccc",4, reference.getStamp()+1);
        System.out.println("reference.getReference() = "+reference.getReference());
        System.out.println("c = " + c);
    }
}
