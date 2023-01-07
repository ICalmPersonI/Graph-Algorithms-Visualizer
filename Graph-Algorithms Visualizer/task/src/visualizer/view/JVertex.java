package visualizer.view;

import visualizer.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class JVertex extends JPanel {

    private static final int HEIGHT = 50;
    private static final int WIGHT = 50;
    private static final String VERTEX_NAME = "Vertex %s";
    private static final String VERTEX_VALUE_NAME = "VertexLabel %s";

    private final long id;
    private final String value;
    private final Point point;
    private boolean isSelected = false;
    private Color selectedColor = Color.YELLOW;

    public JVertex(long id, String value, int x, int y) {
        this.id = id;
        this.value = value;
        this.point = new Point(x, y);

        setSize(WIGHT, HEIGHT);
        setName(String.format(VERTEX_NAME, value));
        setLocation(x - 25, y - 25);
        setBackground(Color.WHITE);
        setOpaque(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        add(new JVertexLabel(value), constraints);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension arcs = new Dimension(50, 50);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isSelected) graphics.setColor(selectedColor);
        else graphics.setColor(Color.WHITE);
        graphics.fillRoundRect(0, 0, WIGHT, HEIGHT, arcs.width, arcs.height);
    }

    public void setSelectedColor(Color color) {
        this.selectedColor = color;
    }

    class JVertexLabel extends JLabel {
        JVertexLabel(String text) {
            setName(String.format(VERTEX_VALUE_NAME, text));
            setFont(Utils.TIMES_ROMAN_28_PLAIN);
            setText(text);
        }

        public JVertex getJVertex() {
            return JVertex.this;
        }
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Point getPoint() {
        return point;
    }
}
