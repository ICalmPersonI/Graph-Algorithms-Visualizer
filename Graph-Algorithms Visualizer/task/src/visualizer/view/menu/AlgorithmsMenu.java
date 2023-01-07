package visualizer.view.menu;

import visualizer.contract.Presenter;
import visualizer.contract.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlgorithmsMenu extends JMenu implements View.AlgorithmsMenu {

    private static final String J_MENU_NAME = "Algorithms";
    private static final String FIRST_ITEM_NAME = View.Graph.Algorithm.DFS.getValue();
    private static final String SECOND_ITEM_NAME = View.Graph.Algorithm.BFS.getValue();
    private static final String THIRD_ITEM_NAME =  View.Graph.Algorithm.DIJKSTRA.getValue();
    private static final String FOURTH_ITEM_NAME = View.Graph.Algorithm.PRIM.getValue();

    private final Presenter.MainFrame presenter;

    public AlgorithmsMenu(Presenter.MainFrame presenter) {
        this.presenter = presenter;
        setText(J_MENU_NAME);
        setName(J_MENU_NAME);
        JMenuItem dfs = new JMenuItem(FIRST_ITEM_NAME);
        dfs.setName(FIRST_ITEM_NAME);
        JMenuItem bfs = new JMenuItem(SECOND_ITEM_NAME);
        bfs.setName(SECOND_ITEM_NAME);
        JMenuItem dijkstra = new JMenuItem(THIRD_ITEM_NAME);
        dijkstra.setName(THIRD_ITEM_NAME);
        JMenuItem prim = new JMenuItem(FOURTH_ITEM_NAME);
        prim.setName(FOURTH_ITEM_NAME);


        dfs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preparing();
                presenter.setSelectedAlgorithm(View.Graph.Algorithm.DFS);
            }
        });

        bfs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preparing();
                presenter.setSelectedAlgorithm(View.Graph.Algorithm.BFS);
            }
        });

        dijkstra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preparing();
                presenter.setSelectedAlgorithm(View.Graph.Algorithm.DIJKSTRA);
            }
        });

        prim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preparing();
                presenter.setSelectedAlgorithm(View.Graph.Algorithm.PRIM);
            }
        });

        add(dfs);
        add(bfs);
        add(dijkstra);
        add(prim);
    }

    private void preparing() {
        presenter.setDefaultColor();
        presenter.setGraphMode(View.Graph.GraphMode.NONE);
        presenter.showInformationPanel();
        presenter.setInformationPanelText(View.InformationPanel.PLEASE_CHOOSE_VERTEX);
    }

}
