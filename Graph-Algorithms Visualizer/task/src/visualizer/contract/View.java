package visualizer.contract;

import visualizer.model.data.Edge;
import visualizer.view.JEdge;
import visualizer.view.JVertex;

import java.awt.*;
import java.util.List;


public interface View {

    interface MainFrame {
        View.Graph getGraph();
        View.GraphModeLabel getGraphModelLabel();
        View.InformationPanel getInformationPanel();
        void exit();
    }

    interface Graph {

        enum Algorithm {
            DFS("Depth-First Search"),
            BFS("Breadth-First Search"),
            DIJKSTRA("Dijkstra's Algorithm"),
            PRIM("Prim's Algorithm");

            private final String value;

            Algorithm(String value) {
                this.value = value;
            }

            public String getValue() {
                return value;
            }
        }

        enum GraphMode {
            ADD_VERTEX("Add a Vertex"),
            ADD_EDGE("Add an Edge"),
            REMOVE_VERTEX("Remove a Vertex"),
            REMOVE_EDGE("Remove an Edge"),
            NONE("None");

            private final String value;

            GraphMode(String value) {
                this.value = value;
            }

            public String getValue() {
                return value;
            }
        }

        String showInputVertexIDDialog();
        int showInputEdgeWeightDialog();
        void addVertex(JVertex vertex);
        void addEdge(JEdge edge, JEdge reverseEdge);
        void removeVertex(JVertex vertex);
        void removeEdge(JEdge edge);
        void drawPath(List<Edge> path, String pathAsString, Color color);
        void drawTemplate(List<Component> components);
        void stopTimers();
        void clear();
        void update();
        void setDefaultColor();
        Algorithm getSelectedAlgorithm();
        void setSelectedAlgorithm(Algorithm algorithm);
    }

    interface GraphModeLabel {
        void updateGraphMode(Graph.GraphMode mode);
    }

    interface ModeMenu {
    }

    interface FileMenu {

    }

    interface AlgorithmsMenu {

    }

    interface TemplatesMenu {
        enum Template {
            ONE, TWO
        }

    }


    interface InformationPanel {
        String PLEASE_WAIT = "Please wait...";
        String PLEASE_CHOOSE_VERTEX = "Please choose a starting vertex";
        String DFS_FORMAT = "DFS : %s";
        String BFS_FORMAT = "BFS : %s";
        String DIJKSTRA_FORMAT = "%s=%d";
        String PRIM_FORMAT = "%s=%s";
        void setText(String text);
        void hidePanel();
        void showPanel();
    }
}
