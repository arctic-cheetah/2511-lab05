package metrics;

import java.util.ArrayList;
import java.util.List;

public class Emitter implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    public void emitMetric(double xValue) {
        Double metric = Math.sin(Math.toRadians(xValue));
        notifyObservers(metric);
    }

    //Add the plot
    public void attach(Observer o) {
        observers.add(o);
    }

    public void notifyObservers(Double o) {
        //When emitMetric is called notify all observers
        observers.forEach(e -> e.update(o));
    }

}
