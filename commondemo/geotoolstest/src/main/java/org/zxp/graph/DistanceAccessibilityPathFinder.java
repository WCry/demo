package org.zxp.graph;

import org.geotools.graph.path.Path;
import org.geotools.graph.structure.Graph;
import org.geotools.graph.structure.Graphable;
import org.geotools.graph.traverse.GraphTraversal;
import org.geotools.graph.traverse.GraphWalker;
import org.geotools.graph.traverse.basic.BasicGraphTraversal;
import org.geotools.graph.traverse.standard.DijkstraIterator;

import java.util.List;

public class DistanceAccessibilityPathFinder implements GraphWalker{
    private DistanceAccessibilityIterator m_iterator;
    private GraphTraversal m_traversal;
    /**
     * Constructs a new path finder.
     *
     * @param graph The graph to calculate paths for.
     * @param iterator The dijsktra iterator to used to calculate shortest paths.
     */
    public DistanceAccessibilityPathFinder(Graph graph, DistanceAccessibilityIterator iterator) {
        m_iterator = iterator;
        m_traversal = new BasicGraphTraversal(graph, this, iterator);
    }

    /**
     * Constructs a new path finder.
     *
     * @param graph Graph to calculate paths for.
     * @param source Node to calculate paths from.
     * @param weighter Associates weights with edges in the graph.
     */
    public DistanceAccessibilityPathFinder(Graph graph, Graphable source, double maxDistance,DijkstraIterator.EdgeWeighter weighter) {
        this(graph, source,maxDistance, weighter, null);
    }

    /**
     * Constructs a new path finder.
     *
     * @param graph Graph to calculate paths for.
     * @param source Node to calculate paths from.
     * @param weighter Associates weights with edges in the graph.
     * @param nweighter Associates weights with nodes in the graph.
     */
    public DistanceAccessibilityPathFinder(
            Graph graph, Graphable source,double maxDistance ,DijkstraIterator.EdgeWeighter weighter,
            DijkstraIterator.NodeWeighter nweighter) {
        m_iterator = new DistanceAccessibilityIterator(weighter, nweighter);
        m_iterator.setSource(source);
        m_iterator.setMaxDistance(maxDistance);
        m_traversal = new BasicGraphTraversal(graph, this, m_iterator);
    }

    /**
     * Performs the graph traversal and calculates the shortest path from the source node to every
     * other node in the graph.
     */
    public void calculate() {
        m_traversal.init();
        m_traversal.traverse();
    }

    /**
     * Returns a path <B>from</B> g <B>to</B> the source. If the desired path is the opposite (from
     * the source to g) can be used.
     *
     * @param g The start node of the path to be calculated.
     * @see Path#riterator()
     * @return A path from g to the source.
     */
    public Path getPath(Graphable g) {
        Path p = new Path();
        p.add(g);

        Graphable parent = g;
        while ((parent = m_iterator.getParent(parent)) != null) p.add(parent);

        if (!p.getLast().equals(m_iterator.getSource())) return (null);

        return (p);
    }

    /**
     * Returns the cost associated with a node calculated during the graph traversal.
     *
     * @param g The node whose cost is desired.
     * @return The cost associated with the node.
     */
    public double getCost(Graphable g) {
        return (m_iterator.getCost(g));
    }

    public DijkstraIterator getIterator() {
        return (m_iterator);
    }

    public GraphTraversal getTraversal() {
        return (m_traversal);
    }

    /**
     * Does nothing except signal the traversal to continue.
     * 标识是否需要进行暂停 继续
     * 可以进行优化 如果到当前节点的为最小值  则其他所有道路都是最大值
     *
     * @see GraphWalker#visit(Graphable, GraphTraversal)
     */
    public int visit(Graphable element, GraphTraversal traversal) {
        return (GraphTraversal.CONTINUE);
    }

    /**
     * Does nothing.
     *
     * @see GraphWalker#finish()
     */
    public void finish() {}

    /**
     * 返回所有能够到达的点
     * @return
     */
    public List<Graphable> getAccessibilityNode(){
        return m_iterator.getAccessibilityNode();
    }
}
