package org.zxp.graph;

import org.geotools.graph.structure.basic.BasicNode;
import org.locationtech.jts.geom.Coordinate;

public class ThreadLocalNode extends BasicNode {
    //通过 ThreadLocal  实现线程安全
    private transient ThreadLocal<Boolean> m_visited;
    private transient ThreadLocal<Integer> m_nvisited;
    private String id;
    private Coordinate coordinate;
    public ThreadLocalNode(){
        super();
        m_visited=new ThreadLocal<>();
        m_nvisited=new ThreadLocal<>();
        setVisited(false);
        setCount(-1);
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

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
