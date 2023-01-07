package visualizer.presenter;

import visualizer.utils.Pair;
import visualizer.utils.Utils;
import visualizer.contract.Model;
import visualizer.contract.Presenter;
import visualizer.contract.View;
import visualizer.model.data.Edge;
import visualizer.model.Graph;
import visualizer.model.data.Vertex;
import visualizer.view.JEdge;
import visualizer.view.JVertex;
import visualizer.view.Template;

import java.awt.*;
import java.util.List;

public class MainFramePresenter implements Presenter.MainFrame {

    private final View.MainFrame view;
    private final Model.Graph model;

    private View.Graph.GraphMode graphMode = View.Graph.GraphMode.ADD_VERTEX;

    public MainFramePresenter(View.MainFrame view) {
        this.view = view;
        this.model = new Graph();
    }

    @Override
    public void addNewVertex(int x, int y) {
        String vertexID = view.getGraph().showInputVertexIDDialog();
        if (vertexID != null) {
            long id = Utils.getNextId();

            JVertex jVertex = new JVertex(id, vertexID, x, y);
            view.getGraph().addVertex(jVertex);
            view.getGraph().update();

            Vertex vertex = new Vertex(jVertex);
            model.addVertex(vertex);
        }
    }

    @Override
    public void addNewEdge(Vertex start, Vertex end) {
        int edgeWeight = view.getGraph().showInputEdgeWeightDialog();
        if (edgeWeight != Integer.MIN_VALUE) {
            long id = Utils.getNextId();

            JEdge jEdge = new JEdge(id, start.getPoint(), end.getPoint(), start.getValue(), end.getValue(), edgeWeight);
            JEdge reverseJEdge = new JEdge(id, end.getPoint(), start.getPoint(), end.getValue(), start.getValue(), edgeWeight);
            view.getGraph().addEdge(jEdge, reverseJEdge);
            view.getGraph().update();

            Edge edge = new Edge(start, end, edgeWeight, jEdge, reverseJEdge);
            model.addEdge(edge);
            start.addEdge(edge);
            end.addEdge(edge);
        }
    }

    @Override
    public void removeVertex(Vertex vertex) {
        view.getGraph().removeVertex(vertex.getView());
        view.getGraph().update();
        model.removeVertex(vertex);
        model.findEdgesThatContainVertex(vertex).forEach(this::removeEdge);
    }

    @Override
    public void removeEdge(Edge edge) {
        view.getGraph().removeEdge(edge.getView());
        view.getGraph().removeEdge(edge.getReverseView());
        view.getGraph().update();
        model.removeEdge(edge);
    }

    @Override
    public void clearGraph() {
        view.getGraph().clear();
        view.getGraph().update();
        model.clear();
    }

    @Override
    public void showDFS(Vertex start) {
        prepareAlgorithm();
        Pair<String, List<Edge>> result = model.getPathOfTraversalByDFS(start);
        String pathAsString = String.format(View.InformationPanel.DFS_FORMAT, result.getFirst());
        List<Edge> path = result.getSecond();
        view.getGraph().drawPath(path, pathAsString, Color.YELLOW);
    }

    @Override
    public void showBFS(Vertex start) {
        prepareAlgorithm();
        Pair<String, List<Edge>> result = model.getPathOfTraversalByBFS(start);
        String pathAsString = String.format(View.InformationPanel.BFS_FORMAT, result.getFirst());
        List<Edge> path = result.getSecond();
        view.getGraph().drawPath(path, pathAsString, Color.orange);
    }

    @Override
    public void showShortestPathByDijkstraAlg(Vertex start) {
        prepareAlgorithm();
        Pair<String, List<Edge>> result = model.getShortestPathByDijkstraAlg(start);
        String pathAsString = result.getFirst();
        List<Edge> path = result.getSecond();
        view.getGraph().drawPath(path, pathAsString, Color.RED);
    }

    @Override
    public void showMinimumSpanningTreeByPrimAgl(Vertex start) {
        prepareAlgorithm();
        Pair<String, List<Edge>> result = model.getMinimumSpanningTreeByPrimAlg(start);
        String treeAsString = result.getFirst();
        List<Edge> tree = result.getSecond();
        view.getGraph().drawPath(tree, treeAsString, Color.PINK);
    }

    @Override
    public void showInformationPanel() {
        view.getInformationPanel().showPanel();
    }

    @Override
    public void hideInformationPanel() {
        view.getInformationPanel().hidePanel();
    }

    @Override
    public void loadTemplate(View.TemplatesMenu.Template template) {
        view.getGraph().clear();
        model.clear();

        switch (template) {
            case ONE -> {
                for (Template.TemplateOne.TemplateForVertex t : Template.TemplateOne.TemplateForVertex.values()) {
                    view.getGraph().addVertex(t.getJVertex());
                    model.addVertex(t.getVertex());
                }
                for (Template.TemplateOne.TemplateForEdge t : Template.TemplateOne.TemplateForEdge.values()) {
                    view.getGraph().addEdge(t.getJEdge(), t.getJEdgeReversed());
                    model.addEdge(t.getEdge());
                }
            }

            case TWO -> {
                for (Template.TemplateTwo.TemplateForVertex t : Template.TemplateTwo.TemplateForVertex.values()) {
                    view.getGraph().addVertex(t.getJVertex());
                    model.addVertex(t.getVertex());
                }
                for (Template.TemplateTwo.TemplateForEdge t : Template.TemplateTwo.TemplateForEdge.values()) {
                    view.getGraph().addEdge(t.getJEdge(), t.getJEdgeReversed());
                    model.addEdge(t.getEdge());
                }
            }
        }

        view.getGraph().update();
    }

    @Override
    public void setDefaultColor() {
        view.getGraph().setDefaultColor();
    }

    @Override
    public void setInformationPanelText(String text) {
        view.getInformationPanel().setText(text);
    }


    @Override
    public void setGraphMode(View.Graph.GraphMode mode) {
        graphMode = mode;
        view.getGraphModelLabel().updateGraphMode(mode);
    }

    @Override
    public View.Graph.GraphMode getGraphMode() {
        return graphMode;
    }

    @Override
    public Vertex getVertexByID(long id) {
        return model.getVertex(id);
    }

    @Override
    public Edge getEdgeByID(long id) {
        return model.getEdge(id);
    }

    @Override
    public View.Graph.Algorithm getSelectedAlgorithm() {
        return view.getGraph().getSelectedAlgorithm();
    }

    @Override
    public void setSelectedAlgorithm(View.Graph.Algorithm algorithm) {
        view.getGraph().setSelectedAlgorithm(algorithm);
    }

    @Override
    public void closeApp() {
        view.exit();
    }

    private void prepareAlgorithm() {
        view.getGraph().setDefaultColor();
        view.getGraph().stopTimers();
        view.getInformationPanel().setText(View.InformationPanel.PLEASE_WAIT);
    }
}
