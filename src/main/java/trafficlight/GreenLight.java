package trafficlight;

public class GreenLight implements State {
    private TrafficLight trafficLight;

    public GreenLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    public String reportState() {
        return "Green light";
    }

    public void change() {
        trafficLight.setCount(1);
        trafficLight.setState(trafficLight.getYellowLight());
    }
}
