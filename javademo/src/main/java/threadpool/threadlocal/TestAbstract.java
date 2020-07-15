package threadpool.threadlocal;

public abstract  class TestAbstract {
    public Integer getInterValue() {
        return InterValue;
    }

    public void setInterValue(Integer interValue) {
        this.InterValue = interValue;
    }

    private Integer InterValue;
}
