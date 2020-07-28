package com.gf;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TransSql {
    private static final String and = "and";
    private static final String or = "or";
    private static final String left = "(";
    private static final String right = ")";

    /**
     * sql 简单能够解析sql的where条件到栈中
     * @param args
     */
    public static void main(String[] args) {
        String querySql = "(rf = 2) and (a = 1 and d=9) or c=0 and (b = 2)";
        querySql = querySql.replaceAll(" ", "");
        List<String> vector = splitBrackets(querySql);
        System.out.println(vector);
    }

    private static List splitBrackets(String s) {
        int indexOfLeft = s.indexOf(left);
        List<String> queryString = new LinkedList<>();
        if (indexOfLeft != -1) {
            if (indexOfLeft != 0) {
                queryString.addAll(splitLogicOperation(s.substring(0, indexOfLeft)));
            }
            int indexOfRight = s.indexOf(right);
            if (indexOfRight != -1) {
                queryString.add(left);
                //进行组合得到一个结果
                queryString.addAll(splitBrackets(s.substring(indexOfLeft + 1, indexOfRight)));
                queryString.add(right);
                queryString.addAll(splitBrackets(s.substring(indexOfRight + 1)));
            } else {
                //左右不匹配
                throw new RuntimeException("转换sql异常");
            }
        } else {
            if (!s.isEmpty()) {
                queryString.addAll(splitLogicOperation(s));
            }
        }
        return queryString;
    }


    private static List splitLogicOperation(String operation) {
        List<String> das = new LinkedList<>();
        List<String> oneStrings = splitLogicOperation(operation, and);
        for (String oneString : oneStrings) {
            if (!oneString.contains(or)) {
                das.add(oneString);
            } else {
                das.addAll(splitLogicOperation(oneString, or));
            }
        }
        return das;
    }

    public static List splitLogicOperation(String splitString, String split) {
        String[] orStrings = splitString.split(split);
        List<String> das = new LinkedList<>();
        if (orStrings.length == 0) {
            das.add(splitString);
            return das;
        }
        for (int j = 0; j < orStrings.length; j++) {
            if (!orStrings[j].isEmpty()) {
                das.add(orStrings[j]);
            }
            if (j != orStrings.length - 1) {
                das.add(split);
            }
        }
        if (splitString.endsWith(and)) {
            das.add(and);
        }
        return das;
    }


    static class Expression {
        Expression valueA;
        Expression valueB;
        String cal;

        public Expression() {
            super();
        }

        public Expression(Expression valueA, Expression valueB, String cal) {
            super();
            this.valueA = valueA;
            this.valueB = valueB;
            this.cal = cal;
        }

        public Expression getValueA() {
            return valueA;
        }

        public void setValueA(Expression valueA) {
            this.valueA = valueA;
        }

        public Expression getValueB() {
            return valueB;
        }

        public void setValueB(Expression valueB) {
            this.valueB = valueB;
        }

        public String getCal() {
            return cal;
        }

        public void setCal(String cal) {
            this.cal = cal;
        }
    }
}
