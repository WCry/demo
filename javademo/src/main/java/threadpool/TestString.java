package threadpool;

import java.util.LinkedList;

/**
 * user:zxp
 * Day:2020,07,16
 **/
public class TestString {
    public static void main(String[] args) {
        String[] srcStrings = {"a", "b", "c"};
        TestString testString = new TestString();
        testString.getSubString(srcStrings);
    }

    public void getSubString(String[] srcString) {
        LinkedList<String[]> linkedList = new LinkedList<>();
        for (int number = 1; number <= srcString.length; number++) {
            for (int j = 0; j < srcString.length - number +1; j++) {
                String[] subString = new String[number];
                for (int w = 0; w < number; w++) {
                    subString[w] = srcString[j+w];
                }
                linkedList.add(subString);
            }
        }
        for (String[] strings : linkedList) {
            for (int i = 0; i < strings.length; i++) {
                System.out.print(strings[i]);
            }
            System.out.println();
        }
    }
}
