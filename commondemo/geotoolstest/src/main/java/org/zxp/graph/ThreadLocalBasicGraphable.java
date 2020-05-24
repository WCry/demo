package org.zxp.graph;

import org.geotools.graph.structure.Graphable;

import java.io.Serializable;

/**
 * 实现线程安全图的遍历
 *     通过ThreadLocal实现线程安全记录状态
 */
public abstract class ThreadLocalBasicGraphable implements Graphable, Serializable {
    private static int id = 0;
    private Object m_obj;
    //通过 ThreadLocal  实现线程安全
    private transient ThreadLocal<Boolean> m_visited;
    private transient ThreadLocal<Integer> m_nvisited;

    /** Id for component. */
    private int m_id;

    /**
     * Constructs a new a graph component. Sets the visited flag to false, counter to -1, and
     * generates a new id.
     */
    public ThreadLocalBasicGraphable() {
        m_visited.set(false);
        m_nvisited.set(-1);
        m_id = id++;
    }

    /** @see Graphable#getID() */
    @Override
    public int getID() {
        return (m_id);
    }

    /** @see Graphable#setID(int) */
    @Override
    public void setID(int id) {
        m_id = id;
    }

    /** @see Graphable#getObject() */
    @Override
    public Object getObject() {
        return (m_obj);
    }

    /** @see Graphable#setObject(Object) */
    @Override
    public void setObject(Object obj) {
        m_obj = obj;
    }

    /** @see Graphable#isVisited() */
    @Override
    public boolean isVisited() {
        return m_visited.get();
    }

    /** @see Graphable#setVisited(boolean) */
    @Override
    public void setVisited(boolean visited) {
        m_visited.set(visited);
    }

    /** @see Graphable#getCount() */
    @Override
    public int getCount() {
        return m_nvisited.get();
    }

    /** @see Graphable#setCount(int) */
    @Override
    public void setCount(int count) {
        m_nvisited.set(count);
    }

    /**
     * Returns the id of the component as a string.
     *
     * @see Graphable#getID()
     */
    @Override
    public String toString() {
        return (String.valueOf(m_id));
    }
}
