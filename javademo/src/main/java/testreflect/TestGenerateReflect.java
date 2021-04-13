package testreflect;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
public class TestGenerateReflect {
    private List<String> testList= new ArrayList<>();
    public static void main(String[] args) throws NoSuchFieldException {
        Class clazz= TestClass.class;
        ParameterizedType pType = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] types = pType.getActualTypeArguments();
        System.out.println("ArrayList继承方式获取具体类型："+types[0].getTypeName());
        //如果ArrList 是非继承方式
        Field listField= TestGenerateReflect.class.getDeclaredField("testList");
        ParameterizedType listType= (ParameterizedType)listField.getGenericType();
        Type[] listTypes =listType.getActualTypeArguments();
        System.out.println("list泛型字段具体类型："+listTypes[0]);
    }
    class  TestClass extends ArrayList<String>{
    }
}
