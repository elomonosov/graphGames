package ru.elomonosov.graph.model;

/**
 * Created on 08.09.2015.
 */
public class Edge implements Comparable{

    private final int source;
    private final int destination;
    private final int weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (destination != edge.destination) return false;
        if (source != edge.source) return false;
        return weight == edge.weight;

    }

    @Override
    public int hashCode() {
        int result = destination;
        result = 31 * result + source;
        result = 31 * result + weight;
        return result;
    }

    public int getDestination() {
        return destination;
    }

    public int getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    public Edge(int source, int destination, int weight) {

        this.destination = destination;
        this.source = source;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "destination=" + destination +
                ", source=" + source +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.weight, ((Edge)o).weight);
    }
}
