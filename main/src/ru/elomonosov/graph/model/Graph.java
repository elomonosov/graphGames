package ru.elomonosov.graph.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 08.09.2015.
 */
public class Graph {

    private final List<Vertex> vertexList;
    private final List<Edge> edgeList;

    public Graph(List<Vertex> vertexList, List<Edge> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public List<Edge> getNeighbors(int vertexId) {
        List<Edge> result = new ArrayList<>();
        for (Edge edge : edgeList) {
            if (edge.getSource() == vertexId) {
                result.add(edge);
            }
        }
        return  result;
    }

    public Set<Edge> getEdges(int vertexId) {
        Set<Edge> result = new HashSet<>();
        for (Edge edge : edgeList) {
            if (edge.getSource() == vertexId) {
                result.add(edge);
            }
        }
        return result;
    }
}
