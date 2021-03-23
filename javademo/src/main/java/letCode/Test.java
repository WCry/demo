package letCode;

import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        add("432", "123");
    }


    static String add(String a, String b) {
        int aLength = 0;
        int bLength = 0;
        if (!Objects.isNull(a)) {
            aLength = a.length() - 1;
        }
        if (!Objects.isNull(b)) {
            bLength = b.length() - 1;
        }
        int i = aLength < bLength ? aLength : bLength;
        int temple = 0;
        StringBuilder result = new StringBuilder();
        while (0 <= i) {
            int aNumber = Character.getNumericValue(a.charAt(i));
            int bNumber = Character.getNumericValue(b.charAt(i));
            int cSum = aNumber + bNumber + temple;
            if (cSum > 10) {
                temple = 1;
                result.append(cSum - 10);
            } else {
                result.append(cSum);
            }
            i--;
        }
        if (i == 0) {
            for (int j = i; j < bLength; j++) {
                int bNumber = Character.getNumericValue(b.charAt(j)) + temple;
                if (bNumber > 10) {
                    temple = 1;
                    result.append(bNumber - 10);
                } else {
                    result.append(bNumber);
                }
            }
        } else {
            for (int j = i; j < aLength; j++) {
                int aNumber = Character.getNumericValue(a.charAt(j)) + temple;
                if (aNumber > 10) {
                    temple = 1;
                    result.append(aNumber - 10);
                } else {
                    result.append(aNumber);
                }
            }
        }
        return result.toString();
    }
}
