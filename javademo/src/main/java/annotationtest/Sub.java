package annotationtest;

@BTable
public class Sub extends Super {
    private int subX;
    public int subY;

    private Sub() {
    }

    public Sub(int i) {
    }

    //私有
    private int subX() {
        return 0;
    }
    //公有
    public int subY() {
        return 0;
    }
}
