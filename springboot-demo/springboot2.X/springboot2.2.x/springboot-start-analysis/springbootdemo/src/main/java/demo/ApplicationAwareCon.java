package demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Configuration
public class ApplicationAwareCon implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, DExtend> dsasd = applicationContext.getBeansOfType(DExtend.class);
        Collection<DExtend> dsadadd=dsasd.values();
        Stream<SuperB> asdsd= dsadadd.stream().flatMap(dExtend -> dExtend.getStreamB());
        List<SuperB>  dsadsad=  asdsd.collect(Collectors.toList());
        dsadsad.forEach(w->{System.out.println(w.getDsad());});
    }
}
