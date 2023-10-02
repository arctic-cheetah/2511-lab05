package metrics.graphing;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import metrics.Emitter;
import metrics.Plot;
import metrics.metricBehaviour.AllPoints;
import metrics.metricBehaviour.AverageRange;
import metrics.metricBehaviour.MaxRange;
import metrics.metricBehaviour.SumRange;

public class Grapher {
    public static void main(String[] args) throws InterruptedException {
        Emitter emitter = new Emitter();
        Plot plot = new Plot(emitter);
        Plot plot2 = new Plot(emitter);

        // Create the GUI on the Event-Dispatch-Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Grapher.createAndShowGUI(plot, "All Points");
                Grapher.createAndShowGUI(plot2, "Average");

            }
        });

        int i = 0;
        while (true) {
            Thread.sleep(100);
            emitter.emitMetric(i);
            i += 5;
        }

    }

    public static Panel createAndShowGUI(Plot plot, String statistic) {
        JFrame frame = new JFrame("Metrics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setSize(800, 600);

        Panel plotPanel = new Panel(plot, statistic);
        plot.setPanel(plotPanel);

        frame.getContentPane().add(plotPanel, BorderLayout.CENTER);

        JComponent controlPanel = createControlPanel(plotPanel);
        frame.getContentPane().add(controlPanel, BorderLayout.EAST);

        frame.setVisible(true);
        return plotPanel;
    }

    private static JComponent createControlPanel(final Panel plotPanel) {

        JPanel controlPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(0, 2));
        controlPanel.add(panel, BorderLayout.NORTH);

        String[] statistics = new String[] {
                "All Points", "Average", "Max", "Sum"
        };

        List<String> tmp = Arrays.asList(statistics);
        final JList<String> statisticsList = new JList<>(statistics);
        int i = tmp.indexOf(plotPanel.getMetricBehaviour().getMetricBehaviourName());
        statisticsList.setSelectedIndex(i);

        panel.add(new JLabel("statistic"));
        panel.add(statisticsList);

        ListSelectionListener changeListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                String statistic = statisticsList.getSelectedValue();
                System.out.println(statistic);
                // TODO Change Strategy based on what button is pressed
                if (statistic.equals(statistics[0])) {
                    plotPanel.setMetricBehaviour(new AllPoints());
                } else if (statistic.equals(statistics[1])) {
                    plotPanel.setMetricBehaviour(new AverageRange());
                } else if (statistic.equals(statistics[2])) {
                    plotPanel.setMetricBehaviour(new MaxRange());
                } else if (statistic.equals(statistics[3])) {
                    plotPanel.setMetricBehaviour(new SumRange());
                }
            }
        };
        statisticsList.addListSelectionListener(changeListener);

        return controlPanel;
    }
}
