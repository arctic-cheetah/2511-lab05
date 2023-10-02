package metrics.metricBehaviour;

import java.util.List;

public class AllPoints implements MetricBehaviour {
    public List<Double> calculateOverInterval(List<Double> data) {
        return data;
    }
}
