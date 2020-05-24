package jsonhandle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
 public class JacksonTester {
        public static void main(String args[]){
            JacksonTester tester = new JacksonTester();
            try {
                ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> studentDataMap = new HashMap<String,Object>();
                int[] marks = {1,2,3};
                Student student = new Student();
                student.setAge(10);
                student.setName("Mahesh");
                // JAVA Object
                studentDataMap.put("student", student);
                // JAVA String
                studentDataMap.put("name", "Mahesh Kumar");
                // JAVA Boolean
                studentDataMap.put("verified", Boolean.FALSE);
                studentDataMap.put("url", new URL("file://D:/"));
                // Array
                studentDataMap.put("marks", marks);
                mapper.writeValue(new File("student.json"), studentDataMap);
                //result student.json
                //{
                //   "student":{"name":"Mahesh","age":10},
                //   "marks":[1,2,3],
                //   "verified":false,
                //   "name":"Mahesh Kumar"
                //}

                System.out.println("class:        "+studentDataMap.get("url").getClass());
                System.out.println(studentDataMap.get("url"));
                System.out.println(studentDataMap.get("name"));
                System.out.println(studentDataMap.get("verified"));
                System.out.println(studentDataMap.get("marks"));
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class Student {
        private String name;
        private int age;
        public Student(){}
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String toString(){
            return "jsonhandle.Student [ name: "+name+", age: "+ age+ " ]";
        }
    }
