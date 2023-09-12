package metrics;

public class Emitter {
    public void emitMetric(double xValue) {
        Double metric = Math.sin(Math.toRadians(xValue));
    }
}
