package letCode.最大回文字符串计算;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            int max = 0;
            for(int i = 0; i < str.length() - 1; i++){
                //aabaa的情况
                max = Math.max(max,longestPalindrome(str,i,i));
                //aabbaa的情况
                max = Math.max(max,longestPalindrome(str,i,i+1));
            }
            System.out.println(max);
        }
    }

    public static int longestPalindrome(String s, int left, int right){
        int len = 0;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            len = right - left + 1;
            left--;
            right++;
        }
        return len;
    }
}
