package trafficlight;

public class RedLight implements State {
    TrafficLight trafficLight;

    public RedLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    public String reportState() {
        return "Red light";
    }

    public void change() {
        if (trafficLight.getNumOfPedestrians() >= 1) {
            trafficLight.setCount(2);
            trafficLight.setState(trafficLight.getPedestrianLight());
        } else {
            int count = trafficLight.getTrafficDemand() > 100 ? 6 : 4;
            trafficLight.setCount(count);
            trafficLight.setState(trafficLight.getGreenLight());
        }

    }
}
