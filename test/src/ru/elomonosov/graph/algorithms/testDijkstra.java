package ru.elomonosov.graph.algorithms;

import org.junit.Test;
import ru.elomonosov.graph.model.Edge;
import ru.elomonosov.graph.model.Graph;
import ru.elomonosov.graph.model.Vertex;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 08.09.2015.
 */
public class testDijkstra {

    @Test
    public void testSimpleConnectedGraph() throws UnsupportedEncodingException {


        List<Edge> edgeList = new ArrayList<>(9);
        List<Vertex> vertexList = new ArrayList<>(6);

        for (int i = 1; i <= 6; i++) {
            vertexList.add(new Vertex(i));
        }

        edgeList.add(new Edge(1, 2, 7));
        edgeList.add(new Edge(1, 3, 9));
        edgeList.add(new Edge(1, 6, 14));
        edgeList.add(new Edge(2, 3, 10));
        edgeList.add(new Edge(2, 4, 15));
        edgeList.add(new Edge(3, 4, 11));
        edgeList.add(new Edge(3, 6, 2));
        edgeList.add(new Edge(6, 5, 9));
        edgeList.add(new Edge(4, 5, 6));

        Graph graph = new Graph(vertexList, edgeList);
        Dijkstra dijkstra = new Dijkstra(graph);

        int startPos = 1;
        Map<Integer, Map<Integer, List<Integer>>> shortestPaths = dijkstra.getShortestPaths(startPos);
        for (Map.Entry<Integer, Map<Integer, List<Integer>>> entry : shortestPaths.entrySet()) {
            Map.Entry<Integer, List<Integer>> pathEntry = entry.getValue().entrySet().iterator().next();
            System.out.println("Length of the shortest path from " + startPos + " to " + entry.getKey() + " is " + pathEntry.getKey() + ". The steps are: " + pathEntry.getValue());
        }
    }

}
