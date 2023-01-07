package visualizer.model.data;

import visualizer.view.JEdge;

import java.util.Objects;

public class Edge {

    private final long id;
    private final Vertex start;
    private final Vertex end;
    private final int weight;
    private final JEdge view;
    private final JEdge reverseView;

    public Edge(Vertex start, Vertex end, int weight, JEdge view, JEdge reverseView) {
        this.id = view.getId();
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.view = view;
        this.reverseView = reverseView;
    }

    public boolean containsVertex(Vertex vertex) {
        return vertex.equals(start) || vertex.equals(end);
    }

    public long getId() {
        return id;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    public JEdge getView() {
        return view;
    }

    public JEdge getReverseView() {
        return reverseView;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return id == edge.id &&
                weight == edge.weight &&
                Objects.equals(start, edge.start) &&
                Objects.equals(end, edge.end) &&
                Objects.equals(view, edge.view) &&
                Objects.equals(reverseView, edge.reverseView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end, weight, view, reverseView);
    }
}
