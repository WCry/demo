package threadpool;

import java.util.List;
import java.util.concurrent.Callable;

public class DownloadTask implements Callable<String> {
    private String current;
    private List<Integer> listIn;
    public DownloadTask(String currentLevel, List<Integer> current){
        this.current=currentLevel;
        listIn=current;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(listIn.size());
        return current;
    }
}
