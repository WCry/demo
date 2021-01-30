package Test;

import java.util.*;

public class Main {
    public static void main(String[] arges) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String l = sc.nextLine();
            if (s.length() == 0 && l.length() == 0) {
                System.out.println("0");
            }
            int slength = s.length();
            int llength = l.length();
            int preIndex = -1;
            int i = 0;
            int j = 0;
            int count=0;
            for (i = 0; i < slength; ) {
                Character character = s.charAt(i);
                for (; j < llength; j++) {
                    if (l.charAt(j) == character) {
                        preIndex = j;
                        count++;
                        break;
                    }
                }
                i++;
            }
            if(count!=slength){
                System.out.println("-1");
            }else{
                System.out.println(preIndex);
            }
        }
        sc.close();
    }
}