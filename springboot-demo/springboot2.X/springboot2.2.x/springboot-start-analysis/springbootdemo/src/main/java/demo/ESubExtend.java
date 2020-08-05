package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
public class ESubExtend extends DExtend<SubC> {
    private SubC superB;

    @Autowired
    public ESubExtend(SubC subC) {
        superB = subC;
    }

    @Override
    public SubC getB() {
        return superB;
    }

    @Override
    protected List<SubC> getList() {
        return new ArrayList<SubC>() {{ add(superB); }};
    }
}
