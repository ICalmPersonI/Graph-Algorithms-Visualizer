package visualizer.model;

import visualizer.contract.Model;
import visualizer.contract.View;
import visualizer.model.data.Edge;
import visualizer.model.data.Vertex;
import visualizer.utils.Pair;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static visualizer.contract.View.InformationPanel.PRIM_FORMAT;

public class Graph implements Model.Graph {

    private static final String RIGHT_ARROW = " -> ";
    private static final String COMMA = ", ";
    private final ArrayList<Vertex> vertices = new ArrayList<>();
    private final ArrayList<Edge> edges = new ArrayList<>();

    @Override
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    @Override
    public void removeVertex(Vertex vertex) {
        vertices.remove(vertex);
    }

    @Override
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    @Override
    public void clear() {
        edges.clear();
        vertices.clear();
    }

    @Override
    public Vertex getVertex(long id) {
        return vertices.stream().filter(v -> v.getId() == id).findFirst().orElseThrow();
    }

    @Override
    public Edge getEdge(long id) {
        return edges.stream().filter(e -> e.getId() == id).findFirst().orElseThrow();
    }

    @Override
    public List<Edge> findEdgesThatContainVertex(Vertex vertex) {
        ArrayList<Edge> result = new ArrayList<>();
        edges.forEach(e -> {
            if (e.containsVertex(vertex)) {
                result.add(e);
            }
        });
        return result;
    }

    @Override
    public Pair<String, List<Edge>> getPathOfTraversalByDFS(Vertex start) {
        LinkedList<Vertex> stack = new LinkedList<>();
        ArrayList<Vertex> visited = new ArrayList<>();
        ArrayList<Edge> path = new ArrayList<>();

        Vertex curr = start;
        stack.add(curr);

        while (true) {
            Edge min = null;

            if (!visited.contains(curr)) {
                visited.add(curr);
            }

            for (Edge edge : curr.getEdges()) {
                Vertex next;
                if (edge.getEnd().equals(curr)) {
                    next = edge.getStart();
                } else {
                    next = edge.getEnd();
                }
                if (!visited.contains(next)) {
                    if (min == null || min.getWeight() > edge.getWeight()) {
                        min = edge;
                        curr = next;
                    }
                }
            }

            if (min == null) {
                if (!stack.isEmpty()) {
                    curr = stack.removeFirst();
                    continue;
                }
                break;
            }

            path.add(min);
            stack.addFirst(curr);
        }

        return new Pair<>(
                String.join(RIGHT_ARROW, visited.stream().map(Vertex::getValue).toList()),
                path.stream().toList()
        );
    }

    @Override
    public Pair<String, List<Edge>> getPathOfTraversalByBFS(Vertex start) {
        Queue<Vertex> queue = new ArrayDeque<>();
        ArrayList<Vertex> visited = new ArrayList<>();
        ArrayList<Edge> path = new ArrayList<>();

        Vertex curr = start;
        visited.add(curr);
        queue.add(curr);

        while (true) {
            Edge min = null;

            for (Edge edge : curr.getEdges()) {
                Vertex next;
                if (edge.getEnd().equals(curr)) {
                    next = edge.getStart();
                } else {
                    next = edge.getEnd();
                }
                if (!visited.contains(next)) {
                    if (min == null || min.getWeight() > edge.getWeight()) {
                        min = edge;
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }

            if (min == null) {
                if (!queue.isEmpty()) {
                    curr = queue.remove();
                    continue;
                }
                break;
            }

            path.add(min);
        }

        return new Pair<>(
                String.join(RIGHT_ARROW, visited.stream().map(Vertex::getValue).toList()),
                path.stream().toList()
        );
    }

    @Override
    public Pair<String, List<Edge>> getShortestPathByDijkstraAlg(Vertex start) {
        Map<Vertex, Integer> distances = vertices.stream().collect(Collectors.toMap(Function.identity(), e -> -1));
        LinkedList<Vertex> stack = new LinkedList<>(vertices);
        ArrayList<Vertex> visited = new ArrayList<>();
        ArrayList<Edge> path = new ArrayList<>();

        Vertex curr = start;
        int totalDist = 0;

        visited.add(curr);
        distances.put(curr, totalDist);

        while (true) {

            Pair<Vertex, Integer> min = null;

            for (Edge edge : curr.getEdges()) {
                Vertex next;

                if (edge.getEnd().equals(curr)) {
                    next = edge.getStart();
                } else {
                    next = edge.getEnd();
                }

                int dist = edge.getWeight() + totalDist;
                if (!visited.contains(next)) {
                    if (min == null || min.getSecond() > dist) {
                        min = new Pair<>(next, dist);
                    }
                    distances.put(next, dist);
                    visited.add(next);
                    path.add(edge);
                }
            }

            if (min == null) {
                if (!stack.isEmpty()) {
                    curr = stack.removeFirst();
                    totalDist = distances.get(curr);
                    continue;
                }
                break;
            }

            curr = min.getFirst();
            totalDist = min.getSecond();
            stack.remove(curr);
        }

        List<String> result = distances.entrySet()
                .stream()
                .filter(e -> !e.getKey().equals(start))
                .sorted(Map.Entry.comparingByKey())
                .map(e -> String.format(View.InformationPanel.DIJKSTRA_FORMAT, e.getKey().getValue(), e.getValue()))
                .toList();
        return new Pair<>(String.join(COMMA, result), path);
    }

    @Override
    public Pair<String, List<Edge>> getMinimumSpanningTreeByPrimAlg(Vertex start) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        ArrayList<Edge> tree = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();

        visited.add(start);
        Consumer<Vertex> addToQueue = vertex -> findEdgesThatContainVertex(vertex).stream()
                .filter(edge -> {
                    if (edge.getEnd().equals(vertex)) {
                        return !visited.contains(edge.getStart());
                    } else {
                        return !visited.contains(edge.getEnd());
                    }
                }).forEach(queue::add);

        addToQueue.accept(start);

        while (!queue.isEmpty()) {

            Edge edge = queue.poll();
            Vertex vertex = edge.getEnd();

            if (visited.contains(vertex)) {
                vertex = edge.getStart();
            }

            if (!visited.contains(vertex)) {
                tree.add(edge);
                visited.add(vertex);
                addToQueue.accept(vertex);
            }
        }

        String treeAsString = String.join(
                COMMA,
                tree.stream()
                        .sorted(Comparator.comparing(Edge::getStart))
                        .map(e -> String.format(PRIM_FORMAT, e.getStart().getValue(), e.getEnd().getValue()))
                        .toList()
        );
        return new Pair<>(treeAsString, tree.stream().toList());
    }
}
