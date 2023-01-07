package visualizer.view.menu;

import visualizer.contract.Presenter;
import visualizer.contract.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemplatesMenu extends JMenu implements View.TemplatesMenu {

    private static final String NAME = "Templates";
    private static final String FIRST_ITEM_NAME = "Template 1";
    private static final String SECOND_ITEM_NAME = "Template 2";

    public TemplatesMenu(Presenter.MainFrame presenter) {
        setText(NAME);
        JMenuItem template1 = new JMenuItem(FIRST_ITEM_NAME);
        JMenuItem template2 = new JMenuItem(SECOND_ITEM_NAME);

        template1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.loadTemplate(Template.ONE);
            }
        });

        template2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.loadTemplate(Template.TWO);
            }
        });


        add(template1);
        add(template2);
    }
}
