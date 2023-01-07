package visualizer.model.data;

import visualizer.view.JVertex;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex implements Comparable<Vertex> {

    private final long id;
    private final String value;
    private final Point point;
    private final JVertex view;
    private final ArrayList<Edge> edges = new ArrayList<>();

    public Vertex(JVertex view) {
        this.id = view.getId();
        this.value = view.getValue();
        this.point = view.getPoint();
        this.view = view;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Point getPoint() {
        return point;
    }

    public JVertex getView() {
        return view;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id == vertex.id &&
                Objects.equals(value, vertex.value) &&
                Objects.equals(point, vertex.point) &&
                Objects.equals(view, vertex.view);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, point, view);
    }


    @Override
    public int compareTo(Vertex o) {
        return value.compareTo(o.getValue());
    }
}
