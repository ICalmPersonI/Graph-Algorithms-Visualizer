package visualizer.view;

import visualizer.contract.Presenter;
import visualizer.contract.View;
import visualizer.model.data.Edge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.List;

public class JGraph extends JPanel implements View.Graph {

    private static final String GRAPH_NAME = "Graph";
    private static final String INPUT_VERTEX_ID_DIALOG_MESSAGE = "Enter the Vertex ID (Should be 1 char):";
    private static final String INPUT_EDGE_WEIGHT_DIALOG_MESSAGE = "Enter Weight";
    private static final String INPUT_VERTEX_ID_DIALOG_TITLE = "Vertex";
    private static final String INPUT_EDGE_WEIGHT_DIALOG_TITLE = "Weight";
    private static final Pattern DIGIT_PATTERN = Pattern.compile("-*\\d++");
    private static final int DRAWING_DELAY = 500;

    private final Presenter.MainFrame presenter;
    private final ArrayList<Timer> timers;
    private Algorithm selectedAlgorithm;

    public JGraph(Presenter.MainFrame presenter) {
        this.presenter = presenter;
        this.timers = new ArrayList<>();
        setName(GRAPH_NAME);
        setBackground(Color.BLACK);
        setLayout(null);

        addMouseListener(new JGraphMouseAdapter(this, presenter));
    }

    @Override
    public String showInputVertexIDDialog() {
        JTextField idField = new JTextField();
        while (true) {
            int result = JOptionPane.showOptionDialog(
                    this, new Object[]{INPUT_VERTEX_ID_DIALOG_MESSAGE, idField},
                    INPUT_VERTEX_ID_DIALOG_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, null, null
            );
            if ((result == JOptionPane.YES_OPTION && idField.getText().length() == 1 && !idField.getText().isBlank())) {
                return idField.getText();
            } else if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                return null;
            }
        }
    }

    @Override
    public int showInputEdgeWeightDialog() {
        JTextField weightField = new JTextField();
        while (true) {
            int result = JOptionPane.showOptionDialog(
                    this, new Object[]{INPUT_EDGE_WEIGHT_DIALOG_MESSAGE, weightField},
                    INPUT_EDGE_WEIGHT_DIALOG_TITLE, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, null, null
            );
            if ((result == JOptionPane.YES_OPTION && !weightField.getText().isBlank())) {
                if (DIGIT_PATTERN.matcher(weightField.getText()).matches()) {
                    return Integer.parseInt(weightField.getText());
                }
            } else if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                return Integer.MIN_VALUE;
            }
        }
    }

    @Override
    public void addVertex(JVertex vertex) {
        add(vertex);
    }

    @Override
    public void addEdge(JEdge edge, JEdge reverseEdge) {
        add(edge);
        add(reverseEdge);

        JLabel edgeLabel = edge.new JEdgeLabel();
        add(edgeLabel);
    }

    @Override
    public void removeVertex(JVertex vertex) {
        remove(vertex);
    }

    @Override
    public void removeEdge(JEdge edge) {
        if (edge.getEdgeLabel() != null) {
            remove(edge.getEdgeLabel());
            edge.setEdgeLabel(null);
        }
        remove(edge);
    }

    @Override
    public void drawPath(List<Edge> path, String pathAsString, Color color) {
        int size = path.size();
        for (int i = 0; i < size; i++) {
            int finalI = i;
            ActionListener listener = new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    Edge edge = path.get(finalI);

                    edge.getView().setSelected(true);
                    edge.getView().setSelectedColor(color);
                    edge.getStart().getView().setSelected(true);
                    edge.getStart().getView().setSelectedColor(color);
                    edge.getEnd().getView().setSelected(true);
                    edge.getEnd().getView().setSelectedColor(color);

                    if (finalI == size - 1) {
                        presenter.setInformationPanelText(pathAsString);
                    }

                    update();
                }
            };

            Timer timer = new Timer(DRAWING_DELAY * (i + 1), listener);
            timers.add(timer);
            timer.setRepeats(false);
            timer.start();

        }
    }

    @Override
    public void drawTemplate(List<Component> components) {
        for (Component component : components) {
            add(component);
        }
    }

    @Override
    public void stopTimers() {
        for (Timer timer : timers) {
            timer.stop();
        }
    }

    @Override
    public void clear() {
        removeAll();
        stopTimers();
    }

    @Override
    public void update() {
        revalidate();
        repaint();
    }

    @Override
    public void setDefaultColor() {
        for (Component component : getComponents()) {
            if (component instanceof JVertex jVertex) {
                jVertex.setSelected(false);
            } else if (component instanceof JEdge jEdge) {
                jEdge.setSelected(false);
            }
        }
        update();
    }

    @Override
    public Algorithm getSelectedAlgorithm() {
        return selectedAlgorithm;
    }

    @Override
    public void setSelectedAlgorithm(Algorithm algorithm) {
        selectedAlgorithm = algorithm;
    }
}
