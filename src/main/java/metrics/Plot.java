package metrics;

import java.util.ArrayList;
import java.util.List;

import metrics.graphing.Panel;

public class Plot implements Observer {
    private Panel panel;
    private List<Double> metrics = new ArrayList<Double>();
    private int range = 150;

    //Attach the emitter to the the plot
    public Plot(Subject o) {
        o.attach(this);
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    // When the emitter fetches new data, add it to the metrics in the plot
    // Then update the gui
    public void update(Double o) {
        metrics.add(o);
        if (panel != null)
            panel.repaint();
    }

    public List<Double> getData() {
        List<Double> newList = new ArrayList<>(metrics);
        if (newList.size() > range) {
            newList = newList.subList(newList.size() - range - 1, newList.size() - 1);
        }
        return newList;
    }
}
