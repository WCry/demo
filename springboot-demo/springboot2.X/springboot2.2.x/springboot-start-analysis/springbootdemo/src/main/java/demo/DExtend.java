package demo;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public abstract class DExtend<B extends SuperB> {
    public abstract B getB();

    public Stream<B> getStreamB() {
        return getList().stream();
    }

    protected abstract List<B> getList();
}
