//import org.zxp.graph.DistanceAccessibilityPathFinder;
//import org.zxp.graph.EdgeWeighter;
//import org.zxp.graph.ThreadLocalNode;
//import org.geotools.graph.structure.Graph;
//import org.geotools.graph.structure.Graphable;
//import org.geotools.graph.structure.Node;
//import org.geotools.graph.traverse.standard.DijkstraIterator;
//import org.locationtech.jts.geom.Coordinate;
//
//import java.io.File;
//import java.util.List;
//
//public class TestDistanceAccessibility {
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
//        //Node startNode=threadLocalGraphGenerator.getNodeByID("1");
//        //按照坐标点进行空间查询到最近的节点  设置容县是100米
//        threadLocalGraphGenerator.setTolerance(100);
//        Node startNode=threadLocalGraphGenerator.getNearestNodeByCoordinate(new
//                Coordinate(120.133830889446,30.1691361954576));
//         // Node endNode=threadLocalGraphGenerator.getNodeByID("12351839");
//        //获取边的权重方式
//        DijkstraIterator.EdgeWeighter weighter=new EdgeWeighter();
//        //基于距离的可达性分析
//        DistanceAccessibilityPathFinder dijkstraShortestPathFinder=new DistanceAccessibilityPathFinder(graph,
//                startNode,400, weighter);
//        dijkstraShortestPathFinder.calculate();
//        // 获取所有能够到达的节点
//        List<Graphable> threadLocalNodes=dijkstraShortestPathFinder.getAccessibilityNode();
//        threadLocalNodes.forEach(value->{
//            ThreadLocalNode threadLocalNode=(ThreadLocalNode)value;
//            //获取对应的ID
//            System.out.println(threadLocalNode.getId());
//            //获取各个路径
//            System.out.println(dijkstraShortestPathFinder.getPath(threadLocalNode));
//        });
//    }
//}
