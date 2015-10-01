package ru.elomonosov.graph.algorithms;

import ru.elomonosov.graph.model.Edge;
import ru.elomonosov.graph.model.Graph;
import ru.elomonosov.graph.model.Vertex;

import java.util.*;

/**
 * Created on 08.09.2015.
 */
public class Dijkstra {

    private final Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    private Map<Integer, Integer> labelVertexes(int startPos) {
        Map<Integer, Integer> vertexLabels = new HashMap<>();
        for (Vertex vertex : graph.getVertexList()) {
            if (vertex.getId() == startPos) {
                vertexLabels.put(vertex.getId(), 0);
            } else {
                vertexLabels.put(vertex.getId(), Integer.MAX_VALUE);
            }
        }
        return vertexLabels;
    }

    /**
     *
     * @param startPos id of start vertex
     * @return vertex mapped to map, which contains the shortest path's length mapped to list of vertex id's that forming the path.
     */
    public Map<Integer, Map<Integer, List<Integer>>> getShortestPaths(int startPos) {
        Map<Integer, Integer> vertexLabels = labelVertexes(startPos);
        Map<Integer, Integer> predecessors  = new HashMap<>(); // map vertex to it predecessor
        Set<Integer> unvisited = new HashSet<>();
        Map<Integer, Integer> pathLengths = new HashMap<>();
        unvisited.add(startPos);
        while (!unvisited.isEmpty()) {
            int nextPos = unvisited.iterator().next();
            List<Edge> neighborEdges = graph.getNeighbors(nextPos);
            for (Edge edge : neighborEdges) {
                unvisited.add(edge.getDestination());
                updateLabels(pathLengths, vertexLabels, predecessors, nextPos);
            }
            unvisited.remove(nextPos);
        }
        Map<Integer, Map<Integer, List<Integer>>> result = new HashMap<>(predecessors.size());
        for (Integer vertexId : predecessors.keySet()) {
            Map<Integer, List<Integer>> lengthWithPath = new HashMap<>(1);
            List<Integer> path = getPath(predecessors, startPos, vertexId, new ArrayList<>());
            Collections.reverse(path);
            lengthWithPath.put(pathLengths.get(vertexId), path);
            result.put(vertexId, lengthWithPath);
        }
        return result;
    }

    private List<Integer> getPath(Map<Integer, Integer> predecessors, int startPos, int finalPos, List<Integer> path) {
        path.add(finalPos);
        if (startPos == finalPos) {
            return path;
        } else {
            int prevPos = predecessors.get(finalPos);
            return getPath(predecessors, startPos, prevPos, path);
        }
    }

    private void updateLabels(Map<Integer, Integer> pathLengths, Map<Integer, Integer> vertexLabels, Map<Integer, Integer> predecessors, int startPos) {
        List<Edge> linkedEdges = graph.getNeighbors(startPos);
        for (Edge linkedEdge : linkedEdges) {
            int newPath = vertexLabels.get(startPos) + linkedEdge.getWeight();
            int oldPath = vertexLabels.get(linkedEdge.getDestination());
            if (newPath < oldPath) {
                if (predecessors != null) {
                    predecessors.put(linkedEdge.getDestination(), linkedEdge.getSource());
                }
                vertexLabels.put(linkedEdge.getDestination(), newPath);
                pathLengths.put(linkedEdge.getDestination(), newPath);
            }
        }
    }
}
