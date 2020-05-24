package org.zxp.graph;

import org.geotools.graph.structure.Graphable;
import org.geotools.graph.structure.Node;
import org.geotools.graph.traverse.GraphTraversal;
import org.geotools.graph.traverse.standard.DijkstraIterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DistanceAccessibilityIterator extends DijkstraIterator {
    //获取设置最大距离权重
    private double maxDistance=Double.MAX_VALUE;
    public DistanceAccessibilityIterator(EdgeWeighter weighter) {
        super(weighter);
    }

    public DistanceAccessibilityIterator(EdgeWeighter weighter, NodeWeighter nweighter) {
        super(weighter, nweighter);
    }

    /**
     * 返回搜索时能够到达的顶点
     * @return
     */
    public List<Graphable> getAccessibilityNode(){
        List<Graphable> graphableList=new LinkedList<>();
        nodemap.forEach((key,value)->{
            if(value.cost!=Double.MAX_VALUE){
                graphableList.add(key);
            }
        });
        return graphableList;
    }

    /**
     */
    @Override
    public void cont(Graphable current, GraphTraversal traversal) {
        DijkstraNode currdn = nodemap.get(current);
        for (Iterator<? extends Graphable> itr = getRelated(current); itr.hasNext(); ) {
            Node related = (Node) itr.next();
            if (!traversal.isVisited(related)) {
                DijkstraNode reldn = nodemap.get(related);
                // calculate cost from current node to related node
                double cost = weighter.getWeight(currdn.node.getEdge(related)) + currdn.cost;
                //如果距离小于最大距离设置，进行更新路径，大于距离不进行继续更新
                if(cost<maxDistance) {
                    // if cost less than current cost of related node, update
                    if (cost < reldn.cost) {
                        queue.remove(reldn);
                        reldn.cost = cost;
                        reldn.parent = currdn;
                        queue.add(reldn);
                    }
                }
            }
        }
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }
}
