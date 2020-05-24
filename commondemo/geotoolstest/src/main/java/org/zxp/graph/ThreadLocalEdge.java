package org.zxp.graph;

import org.geotools.graph.structure.Node;
import org.geotools.graph.structure.basic.BasicEdge;

public class ThreadLocalEdge extends BasicEdge {
    //通过 ThreadLocal  实现线程安全
    private transient ThreadLocal<Boolean> m_visited;
    private transient ThreadLocal<Integer> m_nvisited;
    private String id;
    private double weight;
    /**
     * Constructs a new edge.
     *
     * @param nodeA A node of edge.
     * @param nodeB B node of edge.
     */
    public ThreadLocalEdge(Node nodeA, Node nodeB) {
        super(nodeA, nodeB);
    }
    /**
     * 重写Visited方法实现线程安全的 Visited
     */
    @Override
    public boolean isVisited() {
        return m_visited.get();
    }

    @Override
    public void setVisited(boolean visited) {
        m_visited.set(visited);
    }

    /**
     * 重写Count 实现线程安全的访问次数查询
     */
    @Override
    public int getCount() {
        return m_nvisited.get();
    }

    @Override
    public void setCount(int count) {
        m_nvisited.set(count);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
