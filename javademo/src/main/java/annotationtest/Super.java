package annotationtest;

//作为父类
@ATable
public class Super {
    private int superX;
    public int superY;

    public Super() {
    }
    //私有
    private int superX(){
        return 0;
    }
    //公有
    public int superY(){
        return 0;
    }
}
