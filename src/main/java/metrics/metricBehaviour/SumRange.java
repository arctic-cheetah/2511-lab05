package metrics.metricBehaviour;

import java.util.ArrayList;
import java.util.List;

public class SumRange implements MetricBehaviour {
    private final int WINDOW_SIZE = 10;

    /**
    * Given a list of doubles, where the number of elements is divisible by 10.
    * return an average. 
    * @pre Assume that we receive a list of doubles where the elements are divisible by 10
    * @post return a list of sum over a 10 point window
    * @param data
    * @return
    */

    public List<Double> calculateOverInterval(List<Double> data) {
        List<Double> res = new ArrayList<Double>();
        // Calculate the average over 10 point window
        int j = 0;
        int i = 1;
        int end = data.size();
        while (i < end) {
            if (i % WINDOW_SIZE == 0) {
                List<Double> temp = data.subList(j * WINDOW_SIZE, i);
                res.add(calculateSum(temp));
                j++;
            }
            i += 1;
        }
        // System.out.println(res);
        return res;
    }

    // Helper function to calculate mean over a two point window
    private double calculateSum(List<Double> data) {
        return data.stream().mapToDouble(d -> d).sum();
    }
}
