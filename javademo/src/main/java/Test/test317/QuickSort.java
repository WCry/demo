package Test.test317;

public class QuickSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 3, 9, 2, 3, 14, 2, 64, 32};
        quickSort(0, numbers.length - 1, numbers);
        for (int number : numbers) {
            System.out.println(number);
        }
    }

    public static void quickSort(int left, int right, int[] numbers) {
        if (left >= right) {
            return;
        }
        int stander = numbers[left];
        int leftPoint = left;
        int rightPoint = right;
        while (leftPoint < rightPoint) {
            //从左侧作为标准   所有这里排序 必须从右侧开始 避免重复地方中间点计算失误
            while (leftPoint < rightPoint && numbers[rightPoint] >= stander) {
                rightPoint--;
            }
            //进行交换
            if (leftPoint < rightPoint) {
                numbers[leftPoint++] = numbers[rightPoint];
            }
            while (leftPoint < rightPoint && numbers[leftPoint] <= stander) {
                leftPoint++;
            }
            if (leftPoint < rightPoint) {
                numbers[rightPoint--] = numbers[leftPoint];
            }
        }
        numbers[leftPoint] = stander;
        quickSort(left, leftPoint - 1, numbers);
        quickSort(leftPoint + 1, right, numbers);
    }
}
