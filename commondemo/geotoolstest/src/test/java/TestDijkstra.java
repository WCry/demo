//import org.zxp.graph.EdgeWeighter;
//import org.zxp.graph.ThreadLocalNode;
//import org.geotools.graph.path.DijkstraShortestPathFinder;
//import org.geotools.graph.path.Path;
//import org.geotools.graph.structure.Graph;
//import org.geotools.graph.structure.Node;
//import org.geotools.graph.traverse.standard.DijkstraIterator;
//
//import java.io.File;
//
//public class TestDijkstra {
//    public static void main(String[] args) throws Exception {
//        File file=new File("C:\\Users\\zhangxuepei\\Desktop\\新建文件夹 (2)\\road\\test.txt");
//        //对于图的读取 当前支持读取 暂时为支持写入
//        RoadReaderWriter roadReaderWriter=new RoadReaderWriter();
//        //图的生成器
//        ThreadLocalGraphGenerator threadLocalGraphGenerator=new ThreadLocalGraphGenerator();
//        //构图  采用 ThreadLocal   支持多线程访问
//        roadReaderWriter.setProperty(RoadReaderWriter.GENERATOR,threadLocalGraphGenerator);
//        roadReaderWriter.setProperty(RoadReaderWriter.FILENAME,file);
//        //获取图
//        Graph graph= roadReaderWriter.read();
//        //搜索起始点
//        Node startNode=threadLocalGraphGenerator.getNodeByID("10074255599");
//        Node endNode=threadLocalGraphGenerator.getNodeByID("12351839");
//        //获取边的权重方式
//        DijkstraIterator.EdgeWeighter weighter=new EdgeWeighter();
//        DijkstraShortestPathFinder dijkstraShortestPathFinder=new DijkstraShortestPathFinder(graph,
//                startNode, weighter);
//        dijkstraShortestPathFinder.calculate();
//        System.out.println(dijkstraShortestPathFinder.getCost(endNode));
//        Path path=dijkstraShortestPathFinder.getPath(endNode);
//        for (int i = 0; i < path.size(); i++) {
//            ThreadLocalNode threadLocalNode=(ThreadLocalNode)path.get(i);
//            System.out.println(threadLocalNode.getId());
//        }
//    }
//}
