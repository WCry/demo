package ShpFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        String[] ABCDString = new String[]{"A", "B", "C", "D"};
        String[] ABCEString = new String[]{"A", "B", "C", "E"};
        String[] AHJString = new String[]{"A", "H", "J"};
        List<String> ABCDList = Arrays.asList(ABCDString);
        List<String> ABCEList = Arrays.asList(ABCEString);
        List<String> AHJList = Arrays.asList(AHJString);
        List<List<String>> allLines = new ArrayList<>();
        allLines.add(ABCDList);
        allLines.add(ABCEList);
        allLines.add(AHJList);
        List childList = getList(allLines, ABCDList.get(0),1);

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("00","A");
        resultMap.put("child",childList);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out. println(objectMapper.writeValueAsString(resultMap));
    }

    private static List<Map<String,Object>> getList(List<List<String>> allLines, String pChar,int currentLevel) {
        List<Map<String,Object>> currentLevelList=new LinkedList<>();
        List<String> currentValues=new LinkedList<>();
        for (int i = 0; i < allLines.size(); i++) {
            if (allLines.get(i).size() > currentLevel) {
                String value = allLines.get(i).get(currentLevel);
                String cpChar=allLines.get(i).get(currentLevel-1);
                if(cpChar.equals(pChar)){
                    if (!currentValues.contains(value)) {
                        currentValues.add(value);
                        Map<String, Object> objectMap = new HashMap<>();
                        objectMap.put(currentLevel+""+ i + "", value);
                        List child = new LinkedList();
                        int dd=currentLevel+1;
                        child.addAll(getList(allLines,value, dd));
                        if(child.size()>0){
                            objectMap.put("child",child);
                        }
                        currentLevelList.add(objectMap);
                    }
                }
            }
        }
        return currentLevelList;
    }
}
