//import org.zxp.graph.ThreadLocalEdge;
//import org.zxp.graph.ThreadLocalNode;
//import org.geotools.graph.path.AStarShortestPathFinder;
//import org.geotools.graph.path.Path;
//import org.geotools.graph.structure.Graph;
//import org.geotools.graph.structure.Node;
//import org.geotools.graph.traverse.standard.AStarIterator;
//
//import java.io.File;
//
//public class TestAStartPath {
//    private Graph graph;
//
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
//
//        AStarIterator.AStarFunctions afuncs = new AStarIterator.AStarFunctions(endNode) {
//            @Override
//            public double h(Node n) {
//                ThreadLocalNode threadLocalNode=(ThreadLocalNode)n;
//                ThreadLocalNode desthreadLocalNode=(ThreadLocalNode)this.getDest();
//                return threadLocalNode.getCoordinate().distance(desthreadLocalNode.getCoordinate());
//            }
//            @Override
//            public double cost(AStarIterator.AStarNode n1, AStarIterator.AStarNode n2) {
//                //注意矢量性和有向性
//                ThreadLocalEdge threadLocalEdge=(ThreadLocalEdge)n1.getNode().getEdge(n2.getNode());
//                return threadLocalEdge.getWeight();
//            }
//        };
//        AStarShortestPathFinder finder = new AStarShortestPathFinder(graph, startNode, endNode, afuncs);
//        finder.calculate();
//        Path path = finder.getPath();
//        System.out.println(path.getFirst());
//        System.out.println(path.size());
//    }
//}
