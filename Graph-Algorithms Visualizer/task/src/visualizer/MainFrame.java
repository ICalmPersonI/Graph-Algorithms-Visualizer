package visualizer;

import visualizer.contract.Presenter;
import visualizer.contract.View;
import visualizer.presenter.MainFramePresenter;
import visualizer.view.*;
import visualizer.view.InformationPanel;
import visualizer.view.JGraphModeJLabel;
import visualizer.view.menu.AlgorithmsMenu;
import visualizer.view.menu.FileMenu;
import visualizer.view.menu.ModeMenu;
import visualizer.view.menu.TemplatesMenu;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements View.MainFrame {

    private static final String APP_NAME = "Graph-Algorithms Visualizer";
    private static final int WINDOW_WIGHT = 800;
    private static final int WINDOW_HEIGHT = 600;

    Presenter.MainFrame presenter = new MainFramePresenter(this);

    View.Graph graph = new JGraph(presenter);
    View.GraphModeLabel graphModeLabel = new JGraphModeJLabel(presenter);
    View.ModeMenu modeMenuBar = new ModeMenu(presenter);
    View.FileMenu fileMenuBar = new FileMenu(presenter);
    View.AlgorithmsMenu algorithmsMenu = new AlgorithmsMenu(presenter);
    View.TemplatesMenu templatesMenu = new TemplatesMenu(presenter);
    View.InformationPanel informationLabel = new InformationPanel();

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName(APP_NAME);
        setTitle(APP_NAME);
        setSize(WINDOW_WIGHT, WINDOW_HEIGHT);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.0001;
        add((JLabel) graphModeLabel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.weighty = 1;
        add((JPanel) graph, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 2;
        gbc.weighty = 0.001;
        add((JPanel) informationLabel, gbc);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add((JMenu) fileMenuBar);
        menuBar.add((JMenu) modeMenuBar);
        menuBar.add((JMenu) algorithmsMenu);
        menuBar.add((JMenu) templatesMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    @Override
    public View.Graph getGraph() {
        return graph;
    }

    @Override
    public View.GraphModeLabel getGraphModelLabel() {
        return graphModeLabel;
    }

    @Override
    public View.InformationPanel getInformationPanel() {
        return informationLabel;
    }

    @Override
    public void exit() {
        dispose();
    }
}