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
public class FSubExtend extends DExtend<SubD> {
    private SubD superB;
    @Autowired
    public  FSubExtend(SubD subC){
        superB=subC;
    }
    @Override
    public SubD getB() {
        return superB;
    }

    @Override
    protected List<SubD> getList() {
        return new ArrayList<SubD>(){{add(superB);}};
    }
}
