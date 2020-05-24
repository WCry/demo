package org.zxp.graph;

import org.geotools.graph.structure.Edge;
import org.geotools.graph.traverse.standard.DijkstraIterator;

public class EdgeWeighter implements DijkstraIterator.EdgeWeighter {
    @Override
    public double getWeight(Edge e) {
        ThreadLocalEdge hGISThreadLocalEdge=(ThreadLocalEdge)e;
        return hGISThreadLocalEdge.getWeight();
    }
}
