package visualizer.view;

import visualizer.utils.Utils;
import visualizer.contract.View;

import javax.swing.*;

public class InformationPanel extends JPanel implements View.InformationPanel {

    private static final String CONTENT_NAME = "Display";

    private final JLabel content = new JLabel();

    public InformationPanel() {
        setText(PLEASE_CHOOSE_VERTEX);
        setVisible(false);
        content.setName(CONTENT_NAME);
        content.setFont(Utils.TIMES_ROMAN_14_BOLD);
        add(content);
    }

    @Override
    public void setText(String text) {
        content.setText(text);
        update();
    }

    @Override
    public void hidePanel() {
        setVisible(false);
        update();
    }

    @Override
    public void showPanel() {
        setVisible(true);
        update();
    }

    private void update() {
        revalidate();
        repaint();
    }
}
