public class TestSwitch {
    public static void main(String[] args) {
        char c,b;
        c='a';
        b='a';
        System.out.println(c^b);
        int a=1;
        switch (a) {
            case 2:
                System.out.println("printf 2");
            case 1:
                System.out.println("printf 1");
                default:
                System.out.println("printf default");
            case 3:
                System.out.println("printf 3");
        }
        String params=null;
        switch (params){
            case "params":
                System.out.println("printf params");
                break;
            case "String":
                System.out.println("printf String");
                break;
            case "null":
                System.out.println("printf null");
                break;
            default:
                System.out.println("printf default");
        }
    }
}
