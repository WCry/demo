import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式需要主要 贪婪模式匹配在 字符串很长的时候，发生回溯导致CPU占用过高，俗称Re正则攻击
 * 正则表达式默认是贪婪模式，懒惰模式（在匹配多个长度字符的时候需要后面加上？）
 * 独享模式在长度匹配的过程在后面加上一个+处理
 * *？
 * +？
 * {n,m}?
 * {n,}?
 */
public class TestRegularExpression {
    public static void main(String[] args) {
        testMatcher();
    }

    /**
     * 贪婪模式匹配 默认是贪婪模式匹配
     * 贪婪模式首先尽量更多的匹配，当发现字符串匹配完成，或者不匹配进行回溯
     */
    private static void testGreedy() {
        String jsonstr = "[{\"nodename\":\"任意名称\",\"nodedesc\",\"节点描述\"}]";
        String regex = "\"nodename\":\".*\",";
        System.out.println(jsonstr.replaceAll(regex, ""));
    }

    /**
     * 懒惰加载，匹配到只要符合就开始下一个匹配
     */
    private static void testLazy() {
        String jsonstr = "[{\"nodename\":\"任意名称\",\"nodedesc\",\"节点描述\"}]";
        String regex = "\"nodename\":\".*?\",";
        Matcher matcher = Pattern.compile(regex).matcher(jsonstr);
        System.out.println(matcher.matches());
        System.out.println(jsonstr.replaceAll(regex, ""));
    }


    private static void testMatcher() {
        String str = "123435";
        String regex = "(\\d{1,3}?)(\\d{1,3})";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        matcher.matches();
        System.out.println(matcher.groupCount());
        //分别获取分组的匹配字符串
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
    }
}
