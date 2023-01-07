package visualizer.contract;

import visualizer.model.data.Edge;
import visualizer.model.data.Vertex;

public interface Presenter {
    interface MainFrame {
        void addNewVertex(int x, int y);
        void addNewEdge(Vertex start, Vertex end);
        void removeVertex(Vertex vertex);
        void removeEdge(Edge edge);
        void setGraphMode(View.Graph.GraphMode mode);
        void clearGraph();
        void showDFS(Vertex start);
        void showBFS(Vertex start);
        void showShortestPathByDijkstraAlg(Vertex start);
        void showMinimumSpanningTreeByPrimAgl(Vertex start);
        void showInformationPanel();
        void hideInformationPanel();
        void loadTemplate(View.TemplatesMenu.Template template);
        void setDefaultColor();
        void setInformationPanelText(String text);
        View.Graph.GraphMode getGraphMode();
        Vertex getVertexByID(long id);
        Edge getEdgeByID(long id);
        View.Graph.Algorithm getSelectedAlgorithm();
        void setSelectedAlgorithm(View.Graph.Algorithm algorithm);
        void closeApp();
    }
}
