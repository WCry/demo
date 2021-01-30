package 测试.梅花桩;

import java.util.Scanner;

public class Main {
    public static void main(String[] arges){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int[] numbers=new int[n];
            int[] dp=new int[n];
            for(int i=0;i<n;i++){
                numbers[i]=sc.nextInt();
            }
            for(int i=0;i<n;i++){
                dp[i]=1;
                for(int j=0;j<i;j++){
                    if(numbers[j]<numbers[i]){
                        dp[i]=Math.max(dp[i],dp[j]+1);
                    }
                }
            }
            int max=1;
            for(int i=0;i<n;i++){
                if(dp[i]>max){
                    max=dp[i];
                }
            }
            System.out.println(max);
        }
        sc.close();
    }
}
