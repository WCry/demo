package letCode.动态规划;

public class TestEggsWithFloor {
    public static void main(String[] args) {
       int ans= superEggDrop(2,100);
        System.out.println("测试最优次数："+ans);
    }
    public static int superEggDrop(int K, int N) {
        int[] dp = new int[K + 1];
        int ans = 0;    // 操作的次数
        while (dp[K] < N){
            for (int i = K; i > 0; i--) // 从后往前计算
                dp[i] = dp[i] + dp[i-1] + 1;
            ans++;
        }
        return ans;
    }
}
