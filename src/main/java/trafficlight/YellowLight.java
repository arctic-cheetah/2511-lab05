package trafficlight;

public class YellowLight implements State {
    TrafficLight trafficLight;

    public YellowLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    public String reportState() {
        return "Yellow light";
    }

    public void change() {
        int count = trafficLight.getTrafficDemand() < 10 ? 10 : 6;
        trafficLight.setCount(count);
        trafficLight.setState(trafficLight.getRedLight());
    }
}
