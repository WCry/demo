import com.zxp.Test.EmployeeController;
import com.zxp.Test.SpringbootCacheApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@SpringBootTest(classes= SpringbootCacheApplication.class)
public class ScopeTest {
    @Autowired
    private EmployeeController employeeController;

    @Test
    public void TestResponseEntry() throws UnsupportedEncodingException {
        employeeController.getRequestScope();
    }
}