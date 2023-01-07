package visualizer.view;

import visualizer.contract.Presenter;
import visualizer.contract.View;

import javax.swing.*;
import java.awt.*;

public class JGraphModeJLabel extends JLabel implements View.GraphModeLabel {

    private static final String GRAPH_MODE_LABEL_NAME = "Mode";
    private static final String CURR_MODE_FORMAT = "Current mode -> %s";

    public JGraphModeJLabel(Presenter.MainFrame presenter) {
        setName(GRAPH_MODE_LABEL_NAME);
        setForeground(Color.WHITE);
        setText(String.format(CURR_MODE_FORMAT, presenter.getGraphMode().getValue()));
    }


    @Override
    public void updateGraphMode(View.Graph.GraphMode mode) {
        setText(String.format(CURR_MODE_FORMAT, mode.getValue()));
    }
}
