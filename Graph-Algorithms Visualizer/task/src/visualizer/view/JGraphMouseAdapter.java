package visualizer.view;

import visualizer.contract.Presenter;
import visualizer.model.data.Vertex;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JGraphMouseAdapter extends MouseAdapter {

    private final JGraph graph;
    private final Presenter.MainFrame presenter;

    private JVertex start = null;
    private JVertex end = null;

    JGraphMouseAdapter(JGraph graph, Presenter.MainFrame presenter) {
        this.graph = graph;
        this.presenter = presenter;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component component = graph.getComponentAt(e.getPoint());
        switch (presenter.getGraphMode()) {
            case ADD_VERTEX -> addNewVertexCase(e.getX(), e.getY(), component);
            case ADD_EDGE -> addNewEdgeCase(component);
            case REMOVE_VERTEX -> removeVertexCase(component);
            case REMOVE_EDGE -> removeEdgeCase(component);
            case NONE -> noneCase(component);
        }
    }

    private void addNewVertexCase(int x, int y, Component component) {
        if (component instanceof JGraph) {
            presenter.addNewVertex(x, y);
        }
    }

    private void addNewEdgeCase(Component component) {
        if (component instanceof JVertex || component instanceof JVertex.JVertexLabel) {
            JVertex v;
            if (component instanceof JVertex jVertex) {
                v = jVertex;
            } else {
                JVertex.JVertexLabel jVertexLabel = (JVertex.JVertexLabel) component;
                v = jVertexLabel.getJVertex();
            }

            if (start == null) {
                start = v;
                start.setSelected(true);
                graph.update();
            } else if (end == null) {
                end = v;
                if (start.getId() != end.getId()) {
                    presenter.addNewEdge(presenter.getVertexByID(start.getId()), presenter.getVertexByID(end.getId()));
                }
                start.setSelected(false);
                start = null;
                end = null;
                graph.update();
            }
        }
    }

    private void removeVertexCase(Component component) {
        if (component instanceof JVertex jVertex) {
            presenter.removeVertex(presenter.getVertexByID(jVertex.getId()));
        } else if (component instanceof JVertex.JVertexLabel jVertexLabel) {
            presenter.removeVertex(presenter.getVertexByID(jVertexLabel.getJVertex().getId()));
        }
    }

    private void removeEdgeCase(Component component) {
        if (component instanceof JEdge jEdge) {
            presenter.removeEdge(presenter.getEdgeByID(jEdge.getId()));
        } else if (component instanceof JEdge.JEdgeLabel jEdgeLabel) {
            presenter.removeEdge(presenter.getEdgeByID(jEdgeLabel.getJEdge().getId()));
        }
    }

    private void noneCase(Component component) {
        if (component instanceof JVertex || component instanceof JVertex.JVertexLabel) {
            Vertex vertex;
            if (component instanceof JVertex jVertex) {
                vertex = presenter.getVertexByID(jVertex.getId());
            } else {
                JVertex.JVertexLabel jVertexLabel = (JVertex.JVertexLabel) component;
                vertex = presenter.getVertexByID(jVertexLabel.getJVertex().getId());
            }

            switch (presenter.getSelectedAlgorithm()) {
                case BFS -> presenter.showBFS(vertex);
                case DFS -> presenter.showDFS(vertex);
                case DIJKSTRA -> presenter.showShortestPathByDijkstraAlg(vertex);
                case PRIM -> presenter.showMinimumSpanningTreeByPrimAgl(vertex);
            }
        }
    }
}
