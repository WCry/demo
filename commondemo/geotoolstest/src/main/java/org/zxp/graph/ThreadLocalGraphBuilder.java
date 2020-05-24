package org.zxp.graph;

import org.geotools.graph.build.GraphBuilder;
import org.geotools.graph.build.basic.BasicGraphBuilder;
import org.geotools.graph.structure.Edge;
import org.geotools.graph.structure.Node;

public class ThreadLocalGraphBuilder extends BasicGraphBuilder {
   private boolean removeIsolated =true;
    /**
     * 构建线程安全的节点
     * @return
     */
    @Override
    public Node buildNode() {
        return new ThreadLocalNode();
    }

    /**
     * 构建线程安全的边
     * @param nodeA
     * @param nodeB
     * @return
     */
    @Override
    public Edge buildEdge(Node nodeA, Node nodeB) {
        return new ThreadLocalEdge(nodeA,nodeB);
    }
   
    /** @see GraphBuilder#removeEdge(Edge) */
    public void removeEdge(Edge edge) {
        super.removeEdge(edge);
        //移出图中孤立的点
        if(removeIsolated){
            if(edge.getNodeA().getDegree()==0){
                super.removeNode(edge.getNodeA());
            }
            if(edge.getNodeB().getDegree()==0){
                super.removeNode(edge.getNodeB());
            }
        }
    }

    public boolean isRemoveIsolated() {
        return removeIsolated;
    }

    public void setRemoveIsolated(boolean removeIsolated) {
        this.removeIsolated = removeIsolated;
    }
}
