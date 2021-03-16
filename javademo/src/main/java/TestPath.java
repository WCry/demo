import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestPath {
    public static void main(String[] args) {
        twoSum(new int[]{3,2,4},6);
//        Path path = FileSystems.getDefault().getPath("/dasd/d/dasd");
//        System.out.println(path.subpath(1, 2));
        //泛型接口 不能直接使用
    }
    public void fastSort(int[] a,int left,int right){
        int current=0;
        current=left;

    }
    public static int[] twoSum (int[] numbers, int target) {
        // write code here
        int[] res=new int[]{-1,-1};
        HashMap<Integer,Integer> data=new HashMap();
        for(int i=0;i<numbers.length;i++){
            data.put(numbers[i],i);
        }
        for(int i=0;i<numbers.length;i++){
            int two=target-numbers[i];
            if(data.containsKey(two)&&data.get(two)!=i){
                res[0]=i+1;
                res[1]=data.get(two)+1;
                return res;
            }
        }
        return res;
    }
    public static int search(int[] nums, int target) {
        // write code here
        int left = 0;
        int right = nums.length-1;
        while (left < right) {
            int current = (left + right) / 2;
            if (nums[current] < target) {
                left = current+1;
            } else if(nums[current] > target){
                right = current-1;
            }else{
                right = current;
            }
        }
        if (nums[right] == target) {
            return right;
        }else if(nums[left]== target){
            return left;
        }
        return -1;
    }
}
