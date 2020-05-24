package Kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ｋ_means {
    // 聚类数量
    private final int CLUSTER_SIZE = 4;
    // 聚类中心
    private List<Metadata> clusters;
    // 数据列表
    private List<Metadata> dataList = new ArrayList<Metadata>();
    // 终止标记
    private boolean endflag = false;
    // 噪音阀值
    private final int NOISE_SIZE = 4;
    // 已经进行的次数
    private int round = 0;

    /**
     * 清除噪音
     */
    private boolean clearNoise() {
        // 统计每个聚类中数据的个数
        int[] count = new int[CLUSTER_SIZE];
        for (final Metadata m : dataList) {
            count[m.getMark()]++;
        }
        // 判断是否有噪音
        for (int i = 0; i < CLUSTER_SIZE; i++) {
            if (count[i] < NOISE_SIZE) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算数据到每一个聚类中心的距离
     */
    private void compareCluster() {
        endflag = true;
        // 遍历全部数据，计算最小距离
        for (int j = 0; j < dataList.size(); j++) {
            double dist = Double.MAX_VALUE;
            int mark = dataList.get(j).getMark();
            for (int i = 0; i < CLUSTER_SIZE; i++) {
                double distdata = distanceOfData(dataList.get(j), clusters.get(i));
                if (dist > distdata) {
                    dist = distdata;
                    mark = i;
                }
            }
            // 标记有无数据所在聚类中心变化
            if (mark != dataList.get(j).getMark()) {
                endflag = false;
            }
            dataList.get(j).setMark(mark);
        }
    }

    /**
     * @param data1 第一个数据
     * @param data2 第二个数据
     * @return 他们间的欧式距离的平方
     */
    private double distanceOfData(Metadata data1, Metadata data2) {
        double[] vect1 = data1.getVect();
        double[] vect2 = data2.getVect();
        double sum = 0;
        for (int i = 0; i < Metadata.DATA_VECTOR_SIZE; i++) {
            sum += ((vect1[i] - vect2[i]) * (vect1[i] - vect2[i]));
        }
        return sum;
    }

    /**
     * 开始k-means聚类
     */
    public void kMeansCluster() {
        dataList = Reader.getDatabase();
        // 生成聚类中心并清除噪音，只限第一次选取的聚类中心
        while (!clearNoise()) {
            setCluster();
            compareCluster();
        }
        // 次数加一
        round++;
        //若结束标记为非，继续
        while (!endflag) {
            printClusterData();
            round++;
            setNewCluster();
            compareCluster();
        }
    }

    /**
     * 输出每一次聚类的结果
     */
    private void printClusterData() {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < CLUSTER_SIZE; i++) {
            result.add("");
        }
        for (final Metadata m : dataList) {
            result.set(m.getMark(),
                    result.get(m.getMark()) + " " + m.getNumber());
        }
        System.out.println("" + ": " + round);
        for (int i = 0; i < CLUSTER_SIZE; i++) {
            System.out.println("Cluster: " + i);
            System.out.println("Data: " + result.get(i));
        }
    }

    /**
     * 选取最初的聚类中心
     */
    private void setCluster() {
        Random r = new Random();
        clusters = new ArrayList<Metadata>();
        // 使用随机数
        for (int i = 0; i < CLUSTER_SIZE; i++) {
            Metadata m = dataList.get(r.nextInt(dataList.size()));
            clusters.add(m);
        }
    }

    /**
     * 设置新的聚类中心
     */
    private void setNewCluster() {
        // clusternumbers保存每个聚类的数量
        int[] clusternumbers = new int[CLUSTER_SIZE];
        // count保存它们的个维度元素之和
        List<double[]> count = new ArrayList<double[]>();
        for (int i = 0; i < CLUSTER_SIZE; i++) {
            count.add(new double[Metadata.DATA_VECTOR_SIZE]);
        }
        // 统计各个数据的个维度之和，和每个聚类的数量
        for (final Metadata m : dataList) {
            double[] data = count.get(m.getMark());
            for (int i = 0; i < Metadata.DATA_VECTOR_SIZE; i++) {
                data[i] += m.getVect()[i];
            }
            count.set(m.getMark(), data);
            clusternumbers[m.getMark()]++;
        }
        // 重新生成中心
        for (int i = 0; i < CLUSTER_SIZE; i++) {
            double[] data = count.get(i);
            for (int j = 0; j < Metadata.DATA_VECTOR_SIZE; j++) {
                data[j] = data[j] / clusternumbers[i];
            }
            clusters.get(i).setVect(data);
        }
    }
}
