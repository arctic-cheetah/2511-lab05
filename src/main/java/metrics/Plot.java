package metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import metrics.graphing.Panel;

public class Plot {
    private Panel panel;
    // You may remove the example data for metrics to complete your implementation
    private List<Double> metrics = Arrays.asList(-25.1, -7.7, 69.4, -81.8, -55.6, -74.2, -86.6, 7.6, -71.8, 73.1, -77.2,
            -4.3, 26.4, -90.5, -33.6, 16.8, -92.0, 29.0, 40.3, 50.5, 19.3, 23.5, 47.8, 25.1, 2.2, -35.6, 80.7, 35.0,
            -65.6, 95.0, 5.7, -96.7, -68.3, 59.2, 38.1, -6.9, 6.8, -38.7, 41.8, -3.3, 98.4, 43.7, -53.8, 78.9, -94.1,
            30.0, 36.5, -70.5, -63.8, 87.8);

    private int range = 150;

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public List<Double> getData() {
        List<Double> newList = new ArrayList<>(metrics);
        if (newList.size() > range) {
            newList = newList.subList(newList.size() - range - 1, newList.size() - 1);
        }
        return newList;
    }
}
