package Kmeans;

class Metadata {
    //常数，数据的属性数量
    public static final int DATA_VECTOR_SIZE = 3;
    //保存属性
    private double[] vect = new double[Metadata.DATA_VECTOR_SIZE];
    //数据的编号
    private String number;
    //数据所属的聚类中心编号
    private int mark = 0;
    public Metadata() {
    }

    // 使用构造方法直接对自身的属性赋值
    public Metadata(String[] strToknizer) {
        setNumber(strToknizer[0]);
        for (int i = 1; i < strToknizer.length; i++) {
            vect[i - 1] = Double.parseDouble(strToknizer[i]);
        }
    }

    public double[] getVect() {
        return vect;
    }

    public void setVect(double[] vect) {
        this.vect = vect;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
