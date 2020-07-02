package com.zxp;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestInstance {
    private static TestInstance instance = new TestInstance();
    private String name = "";

    public static TestInstance getInstance() {
        System.out.println(TestInstance.class.getClassLoader());
        return instance;
    }

    public String getString() {
        return this.name;
    }

    public void setString(String name) {
        if (this.name.isEmpty()) {
            this.name = name;
        }
    }
}
