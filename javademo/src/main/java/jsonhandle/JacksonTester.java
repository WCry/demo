package jsonhandle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.ls.LSInput;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class JacksonTester {


    public static void main(String args[]) throws IOException {
        Integer a = 111111111;
        Integer b = 20;
        Integer c = a * b;
        System.out.println(c);
        System.out.println(Integer.MAX_VALUE);
        // TestList();
        // TestMap();
    }


    public static void TestMap() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> studentDataMap = new HashMap<String, Object>();
            int[] marks = {1, 2, 3};
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
            JsonNode jsonNode = mapper.readTree(new File("student.json"));
            System.out.println("class:        " + jsonNode.get("url").getClass());
            System.out.println(jsonNode.get("url"));
            System.out.println(jsonNode.get("name"));
            System.out.println(jsonNode.get("verified"));
            System.out.println(jsonNode.get("marks"));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void TestList() throws IOException {
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setAge(10);
        student.setName("Mahesh");
        Student student2 = new Student();
        student2.setAge(10);
        student2.setName("Mahesh2");
        students.add(student);
        students.add(student2);
        ObjectMapper objectMapper = new ObjectMapper();
        //转换一个Json的 集合对象
        List<Student> studentsConvert = objectMapper.readValue(objectMapper.writeValueAsString(students), new TypeReference<List<Student>>() {});
        System.out.println(studentsConvert.get(0).getName());
    }

    public static void testHashSet() throws IOException {
        Time.Student student=new Time.Student();
        student.setName("A");
        Time.Student student1=new Time.Student();
        student1.setName("A");
        List<Time.Student> ds=new ArrayList<>();
        ds.add(student);
        ds.add(student1);
        ObjectMapper objectMapper=new ObjectMapper();
        String dsad= objectMapper.writeValueAsString(ds);
        HashSet<Time.Student> hashSet=objectMapper.readValue(dsad,new TypeReference<HashSet<Time.Student>>(){});
    }
}


class Student {
    private String name;
    private int age;

    public Student() {}

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

    public String toString() {
        return "jsonhandle.Time.Student [ name: " + name + ", age: " + age + " ]";
    }
}
