package metrics.graphing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

import metrics.Plot;
import metrics.metricBehaviour.AllPoints;
import metrics.metricBehaviour.AverageRange;
import metrics.metricBehaviour.MaxRange;
import metrics.metricBehaviour.MetricBehaviour;
import metrics.metricBehaviour.SumRange;

public class Panel extends JPanel {
    private static final long serialVersionUID = -6588061082489436970L;
    private int marg = 60;
    private int numberYDivisions = 10;
    private int pointWidth = 4;
    private Color gridColor = new Color(200, 200, 200, 200);
    private MetricBehaviour calculateData = new AllPoints();
    private Plot plot;
    private Color dotColour = new Color((int) (255 * Math.random()), (int) (255 * Math.random()),
            (int) (255 * Math.random()));

    public Panel(Plot plot, String statistic) {
        String[] statistics = new String[] {
                "All Points", "Average", "Max", "Sum"
        };
        if (statistic.equals(statistics[0])) {
            setMetricBehaviour(new AllPoints());
        } else if (statistic.equals(statistics[1])) {
            setMetricBehaviour(new AverageRange());
        } else if (statistic.equals(statistics[2])) {
            setMetricBehaviour(new MaxRange());
        } else if (statistic.equals(statistics[3])) {
            setMetricBehaviour(new SumRange());
        }
        this.plot = plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintAxes(g);
        paintData(g);
    }

    private void paintAxes(Graphics2D g) {
        g.setColor(Color.BLACK);

        int width = getWidth();
        int height = getHeight();
        g.draw(new Line2D.Double(marg, marg, marg, height - marg));
        g.draw(new Line2D.Double(marg, height / 2, width - marg, height / 2));
    }

    private void paintData(Graphics2D g) {
        List<Double> emitterData = plot.getData();
        // TODO change data based on statistic
        // Insert MetricBehaviour here
        emitterData = calculateData.calculateOverInterval(emitterData);

        double x = (double) (getWidth() - 2 * marg) / (emitterData.size() - 1);
        double scale = (double) (getHeight() - 2 * marg)
                / (Math.max(Math.abs(getMinValue(emitterData)), Math.abs(getMaxValue(emitterData))) * 2);
        paintGridLines(g, emitterData);
        g.setColor(dotColour);

        // set points to the graph
        for (int i = 0; i < emitterData.size(); i++) {
            double x1 = marg + i * x;
            double y1 = getHeight() / 2 + emitterData.get(i) * scale;
            g.fill(new Ellipse2D.Double(x1 - pointWidth / 2, y1 - pointWidth / 2, pointWidth, pointWidth));
        }
    }

    private void paintGridLines(Graphics2D g, List<Double> emitterData) {
        g.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = marg;
            int x1 = pointWidth + marg;
            int y0 = getHeight() - ((i * (getHeight() - marg * 2)) / numberYDivisions + marg);
            int y1 = y0;
            if (emitterData.size() > 0) {
                g.setColor(gridColor);
                g.drawLine(marg + 1 + pointWidth, y0, getWidth() - marg, y1);
                g.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinValue(emitterData)
                        + (getMaxValue(emitterData) - getMinValue(emitterData)) * ((i * 1.0) / numberYDivisions))
                        * 100)) / 100.0 + "";
                FontMetrics metrics = g.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g.drawLine(x0, y0, x1, y1);
        }

        // for x axis
        for (int i = 0; i < emitterData.size(); i++) {
            if (emitterData.size() > 1) {
                int x0 = i * (getWidth() - marg * 2) / (emitterData.size() - 1) + marg;
                int x1 = x0;
                int y0 = getHeight() - marg;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((emitterData.size() / 20.0)) + 1)) == 0) {
                    g.setColor(gridColor);
                    g.drawLine(x0, getHeight() - marg - 1 - pointWidth, x1, marg);
                }
                g.drawLine(x0, y0, x1, y1);
            }
        }
    }

    private double getMinValue(List<Double> emitterData) {
        double minValue = Double.MAX_VALUE;
        for (Double value : emitterData) {
            minValue = Math.min(minValue, value);
        }
        return minValue;
    }

    private double getMaxValue(List<Double> emitterData) {
        double maxValue = Double.MIN_VALUE;
        for (Double value : emitterData) {
            maxValue = Math.max(maxValue, value);
        }
        return maxValue;
    }

    public MetricBehaviour getMetricBehaviour() {
        return calculateData;
    }

    public void setMetricBehaviour(MetricBehaviour calculateData) {
        this.calculateData = calculateData;
    }

}
