import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class dsad {
    public static void main(String[] arges){
        Map<Integer,Integer> numbers2=new HashMap<>();
        numbers2.put(1,5);
        numbers2.computeIfAbsent(1, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return null;
            }
        });
        System.out.println(Character.getNumericValue('2'));


        numbers2.computeIfPresent(1, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer2+5;
            }
        });
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