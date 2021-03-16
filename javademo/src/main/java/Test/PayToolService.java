package Test;

import java.util.concurrent.ExecutionException;

public interface PayToolService{
    boolean avaliable();
    PayToolEnum getPayTool() throws ExecutionException, InterruptedException;
}
