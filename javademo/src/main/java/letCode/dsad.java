package letCode;

import java.util.*;

public class dsad {
    public static void main(String[] arges){
        Map<Integer,Integer> numbers2=new HashMap<>();
        numbers2.put(1,5);
        numbers2.computeIfAbsent(1, integer -> null);
        System.out.println(Character.getNumericValue('2'));
        numbers2.computeIfPresent(1, (integer, integer2) -> integer2+5);
        Scanner sc=new Scanner(System.in);
        Long number=sc.nextLong();
        List<Integer> numbers=new LinkedList();
        while(!isPrime(number)){
            int maxIter=(int)Math.sqrt(number*1.0);
            for(int i=2;i<=maxIter;i++){
                if(number%i==0){
                    number=number/i;
                    numbers.add(i);
                    break;
                }
            }
        }
        numbers.add(number.intValue());
        Collections.sort(numbers);
        for(Integer out:numbers){
            System.out.print(out+" ");
        }
    }
    public static Boolean isPrime(double n){
        if(n==2){
            return true;
        }
        int maxIter=(int)Math.sqrt(n);
        for(int i=2;i<=maxIter;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
}