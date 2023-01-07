package visualizer.contract;

import visualizer.model.data.Edge;
import visualizer.model.data.Vertex;
import visualizer.utils.Pair;

import java.util.List;
import java.util.Map;

public interface Model {

    interface Graph {
        void addVertex(Vertex vertex);
        void removeVertex(Vertex vertex);
        void addEdge(Edge edge);
        void removeEdge(Edge edge);
        void clear();
        Vertex getVertex(long id);
        Edge getEdge(long id);
        List<Edge> findEdgesThatContainVertex(Vertex vertex);
        Pair<String, List<Edge>> getPathOfTraversalByDFS(Vertex start);
        Pair<String, List<Edge>> getPathOfTraversalByBFS(Vertex start);
        Pair<String, List<Edge>> getShortestPathByDijkstraAlg(Vertex start);
        Pair<String, List<Edge>> getMinimumSpanningTreeByPrimAlg(Vertex start);
    }
}
