package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class Json {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Apple fruit = new Apple();
        Bucket bucket = new Bucket();
        bucket.setFruit(fruit);
        String dsad = objectMapper.writeValueAsString(bucket);
        System.out.println(objectMapper.writeValueAsString(bucket));
        bucket = objectMapper.readValue(dsad, Bucket.class);
        System.out.println(bucket.getFruit().getPrice());
    }

    interface Fruit extends Serializable {
        int getPrice();
    }

    static class Bucket implements Serializable {
        private static final long serialVersionUID = -1556567202861388640L;
        private Fruit fruit;

        public Fruit getFruit() {
            return fruit;
        }

        public void setFruit(Fruit fruit) {
            this.fruit = fruit;
        }
    }

    static class Apple implements Fruit {
        private int price;

        @Override
        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
