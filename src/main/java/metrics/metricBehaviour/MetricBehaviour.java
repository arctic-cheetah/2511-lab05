package metrics.metricBehaviour;

import java.util.List;

public interface MetricBehaviour {
    // Do not mutate the data from plot
    /**
     * Given the data, apply the calculations over an interval,
     * and return a new copy of the transformed data
     * @param data
     * @return 
     */
    public List<Double> calculateOverInterval(List<Double> data);
}
