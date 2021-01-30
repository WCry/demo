package Test.dsad;

import java.util.*;

public class Main {
    static class People {
        Integer[] scords = new Integer[]{-1, -1, -1};
        Integer Id;
        Integer count = 0;
        public People(Integer id) {
            this.Id = id;
        }
        public Boolean success() {
            return count == 3 ? true : false;
        }

        public Integer getScord() {
            return scords[0] + scords[1] + scords[2];
        }

        public void addScords(Integer scord) {
            if (count < 3) {
                scords[count] = scord;
                count++;
                return;
            }
            int minindex = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < count; i++) {
                if (scords[i] < min) {
                    min = scords[i];
                    minindex = i;
                }
            }
            scords[minindex] = scord;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            scanner.nextLine();
            String lineID = scanner.nextLine();
            String[] Ids = lineID.split(",");
            String lineScords = scanner.nextLine();
            String[] scords = lineScords.split(",");
            Map<Integer, People> peopleMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                Integer id = Integer.parseInt(Ids[i]);
                Integer scord = Integer.parseInt(scords[i]);
                peopleMap.computeIfPresent(id, (key, value) -> {
                    value.addScords(scord);
                    return value;
                });
                peopleMap.computeIfAbsent(id, integer -> {
                    People pe = new People(id);
                    pe.addScords(scord);
                    return pe;
                });
            }
            Set<Map.Entry<Integer, People>> entry = peopleMap.entrySet();
            Iterator<Map.Entry<Integer, People>> entryIterator = entry.iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<Integer, People> current = entryIterator.next();
                if (!current.getValue().success()) {
                    entryIterator.remove();
                }
            }
            entry = peopleMap.entrySet();
            entryIterator = entry.iterator();
            Map<Integer,List<Integer>>  scordOrders=new TreeMap<>((a,b)-> b-a);
            while (entryIterator.hasNext()) {
                Map.Entry<Integer, People> current = entryIterator.next();
                Integer scord= current.getValue().getScord();
                Integer id=current.getKey();
                scordOrders.computeIfPresent(scord,(key,value)->{
                    value.add(id);
                    return value;
                });
                scordOrders.computeIfAbsent(scord, integer -> {
                    List<Integer> ids=new LinkedList<>();
                    ids.add(id);
                    return ids;
                });
            }
            String soutString="";
            for(Map.Entry<Integer,List<Integer>> scordOrder: scordOrders.entrySet()){
                List<Integer> ids= scordOrder.getValue();
                Collections.sort(ids,(a,b)-> b-a);
                for (Integer id : ids) {
                    soutString=soutString+id+",";
                }
            }
            soutString=soutString.substring(0,soutString.length()-1);
            System.out.println(soutString);
        }
    }
}
