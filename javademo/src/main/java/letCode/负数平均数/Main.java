package letCode.负数平均数;

import  java.util.*;
public class Main {
    public static void main(String[] arges){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int number=sc.nextInt();
            if(number<0){
                System.out.println(number);
            }
        }
    }
}
