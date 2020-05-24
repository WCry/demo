package genericdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 扩展 泛型测试
 **/
public class GenericExtendsTest {
    static class Food {}
    static class Fruit extends Food {}
    static class Apple extends Fruit {}

    public static void main(String[] args) throws IOException {
        List<? extends Fruit> fruits = new ArrayList<>();
//        fruits.add(new Food());     // compile error
//        fruits.add(new Fruit());    // compile error
//        fruits.add(new Apple());    // compile error

        fruits = new ArrayList<Fruit>(); // compile success
        fruits = new ArrayList<Apple>(); // compile success
     //   fruits = new ArrayList<Food>(); // compile error
      //  fruits = new ArrayList<? extends Fruit>(); // compile error: 通配符类型无法实例化

        Fruit object = fruits.get(0);    // compile success
    }
}
