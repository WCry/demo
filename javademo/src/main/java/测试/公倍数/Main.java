package 测试.公倍数;

import java.util.*;
public class Main{
    public static void main(String[] arg){
        Scanner s=new Scanner(System.in);
        while(s.hasNext()){
            String input=s.nextLine();
            String[] arr=input.split(" ");
            System.out.println(getPrime(Integer.valueOf(arr[0]),
                    Integer.valueOf(arr[1])));
        }
    }

    private static int getPrime(int number,int number1){
        int count=Math.min(number,number1);
        int numberTemp=number;
        int number1Temp=number1;
        int result=1;
        for(int i=2;i<=count;i++){
            while(number%i==0&&number1%i==0){
                number=number/i;
                number1=number1/i;
                result=result*i;
            }
        }
        return numberTemp*number1Temp/result;
    }
}
