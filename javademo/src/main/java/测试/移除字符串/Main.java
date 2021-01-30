package 测试.移除字符串;

import java.util.*;
public class Main {

    public static void main(String[] arges){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            List<Integer> numbers=new LinkedList();
            numbers.add(sc.nextInt());
            for(int i=1;i<n;i++){
                int nextValue=sc.nextInt();
                int pretValue=sc.nextInt();
                int preindex=numbers.indexOf(pretValue);
                numbers.add(preindex+1,nextValue);
            }
            Integer removeNumber=sc.nextInt();
            numbers.remove(removeNumber);
            numbers.forEach(a->System.out.print(a+" "));
            System.out.println();
        }
    }

}