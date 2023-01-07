package visualizer.view;

import visualizer.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class JEdge extends JComponent {

    private static final String EDGE_NAME_FORMAT = "Edge <%s -> %s>";
    private static final float EDGE_THICKNESS = 5.0f;

    private final long id;
    private final Point start;
    private final Point end;
    private final String startValue;
    private final String endValue;
    private final int weight;
    private final Line2D.Float line;
    private JEdgeLabel edgeLabel = null;
    private boolean isSelected = false;
    private Color selectedColor = Color.YELLOW;

    public JEdge(long id, Point start, Point end, String startValue,
                 String endValue, int weight) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.startValue = startValue;
        this.endValue = endValue;
        this.weight = weight;
        setName(String.format(EDGE_NAME_FORMAT, startValue, endValue));

        line = new Line2D.Float(start, end);
        setSize(getPreferredSize());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isSelected) g2.setColor(selectedColor);
        else g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(EDGE_THICKNESS));
        g2.draw(line);
    }

    @Override
    public Dimension getPreferredSize() {
        Rectangle bounds = line.getBounds();
        int width = bounds.x + bounds.width;
        int height = bounds.y + bounds.height;
        return new Dimension(width, height);
    }

    @Override
    public boolean contains(int x, int y) {
        double distance = line.ptSegDist(new Point2D.Double(x, y));
        return distance < 2;
    }

    public void setSelectedColor(Color color) {
        this.selectedColor = color;
    }

    public class JEdgeLabel extends JLabel {

        private static final String EDGE_LABEL_FORMAT = "EdgeLabel <%s -> %s>";

        JEdgeLabel() {
            float x1 = start.x;
            float x2 = end.x;
            float y1 = start.y;
            float y2 = end.y;
            int xMidpoint = (int) ((x2 + x1) / 2) - 25;
            int yMidpoint = (int) ((y2 + y1) / 2) - 25;
            setText(String.valueOf(weight));
            setName(String.format(EDGE_LABEL_FORMAT, startValue, endValue));
            setLocation(xMidpoint, yMidpoint);
            setForeground(Color.WHITE);
            setSize(30, 30);
            setFont(Utils.TIMES_ROMAN_24_PLAIN);
            setEdgeLabel(this);
        }

        public JEdge getJEdge() {
            return JEdge.this;
        }
    }

    public long getId() {
        return id;
    }

    public void setEdgeLabel(JEdgeLabel edgeLabel) {
        this.edgeLabel = edgeLabel;
    }

    public JEdgeLabel getEdgeLabel() {
        return this.edgeLabel;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
