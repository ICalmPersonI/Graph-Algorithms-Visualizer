package visualizer.view.menu;

import visualizer.contract.Presenter;
import visualizer.contract.View;
import visualizer.view.InformationPanel;

import javax.swing.*;
import java.awt.event.*;

public class ModeMenu extends JMenu implements View.ModeMenu {

    private static final String J_MENU_NAME = "Mode";
    private static final String FIRST_ITEM_NAME = View.Graph.GraphMode.ADD_VERTEX.getValue();
    private static final String SECOND_ITEM_NAME = View.Graph.GraphMode.ADD_EDGE.getValue();
    private static final String THIRD_ITEM_NAME = View.Graph.GraphMode.REMOVE_VERTEX.getValue();
    private static final String FOURTH_ITEM_NAME = View.Graph.GraphMode.REMOVE_EDGE.getValue();
    private static final String FIFTH_ITEM_NAME = View.Graph.GraphMode.NONE.getValue();

    public ModeMenu(Presenter.MainFrame presenter) {
        setText(J_MENU_NAME);
        setName(J_MENU_NAME);
        JMenuItem addVertex = new JMenuItem(FIRST_ITEM_NAME);
        addVertex.setName(FIRST_ITEM_NAME);
        JMenuItem addEdge = new JMenuItem(SECOND_ITEM_NAME);
        addEdge.setName(SECOND_ITEM_NAME);
        JMenuItem removeVertex = new JMenuItem(THIRD_ITEM_NAME);
        removeVertex.setName(THIRD_ITEM_NAME);
        JMenuItem removeEdge = new JMenuItem(FOURTH_ITEM_NAME);
        removeEdge.setName(FOURTH_ITEM_NAME);
        JMenuItem none = new JMenuItem(FIFTH_ITEM_NAME);
        none.setName(FIFTH_ITEM_NAME);

        addVertex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.setGraphMode(View.Graph.GraphMode.ADD_VERTEX);
                presenter.hideInformationPanel();
            }
        });

        addEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.setGraphMode(View.Graph.GraphMode.ADD_EDGE);
                presenter.hideInformationPanel();
            }
        });

        removeVertex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.setGraphMode(View.Graph.GraphMode.REMOVE_VERTEX);
                presenter.hideInformationPanel();
            }
        });

        removeEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.setGraphMode(View.Graph.GraphMode.REMOVE_EDGE);
                presenter.hideInformationPanel();
            }
        });

        none.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.setGraphMode(View.Graph.GraphMode.NONE);
            }
        });

        add(addVertex);
        add(addEdge);
        add(removeVertex);
        add(removeEdge);
        add(none);
    }
}
