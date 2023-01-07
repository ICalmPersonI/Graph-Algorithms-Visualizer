package visualizer.view.menu;

import visualizer.contract.Presenter;
import visualizer.contract.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenu extends JMenu implements View.FileMenu {

    private static final String J_MENU_NAME = "File";
    private static final String FIRST_ITEM_NAME = "New";
    private static final String SECOND_ITEM_NAME = "Exit";

    public FileMenu(Presenter.MainFrame presenter) {
        setText(J_MENU_NAME);
        setName(J_MENU_NAME);
        JMenuItem newFile = new JMenuItem(FIRST_ITEM_NAME);
        newFile.setName(FIRST_ITEM_NAME);
        JMenuItem exit = new JMenuItem(SECOND_ITEM_NAME);
        exit.setName(SECOND_ITEM_NAME);

        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.clearGraph();
                presenter.hideInformationPanel();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.closeApp();
            }
        });

        add(newFile);
        add(exit);
    }

}
