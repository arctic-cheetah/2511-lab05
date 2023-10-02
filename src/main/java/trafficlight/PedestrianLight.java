package trafficlight;

public class PedestrianLight implements State {
    TrafficLight trafficLight;

    public PedestrianLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    public String reportState() {
        return "Pedestrian light";
    }

    public void change() {
        int count = trafficLight.getTrafficDemand() > 100 ? 6 : 4;
        trafficLight.setCount(count);
        trafficLight.setState(trafficLight.getGreenLight());
    }
}
