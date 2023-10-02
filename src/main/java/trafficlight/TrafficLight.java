package trafficlight;

public class TrafficLight {
    private State state;
    private int trafficDemand;
    private int numOfPedestrians;
    private State greenLight;
    private State yellowLight;
    private State redLight;
    private State pedestrianLight;

    private int count = 0;

    public TrafficLight(String state, String id) {
        this.redLight = new RedLight(this);
        this.greenLight = new GreenLight(this);
        this.yellowLight = new YellowLight(this);
        this.pedestrianLight = new PedestrianLight(this);

        // Initial states
        if (state.equals("Red light")) {
            this.state = redLight;
            this.count = 6;
        } else if (state.equals("Green light")) {
            this.state = greenLight;
            this.count = 4;
        } else if (state.equals("Yellow light")) {
            this.state = yellowLight;
            this.count = 1;
        } else if (state.equals("Pedestrian light")) {
            this.state = pedestrianLight;
            this.count = 2;
        }
    }

    public void change(int numOfCars, int numOfPedestrians) {
        if (count > 0) {
            count -= 1;
            return;
        }
        setNumOfPedestrians(numOfPedestrians);
        setTrafficDemand(numOfCars + numOfPedestrians);
        state.change();
    }

    public int timeRemaining() {
        return count;
    }

    public String reportState() {
        return state.reportState();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getTrafficDemand() {
        return trafficDemand;
    }

    public State getGreenLight() {
        return greenLight;
    }

    public State getYellowLight() {
        return yellowLight;
    }

    public State getRedLight() {
        return redLight;
    }

    public void setTrafficDemand(int trafficDemand) {
        this.trafficDemand = trafficDemand;
    }

    public State getPedestrianLight() {
        return pedestrianLight;
    }

    public int getNumOfPedestrians() {
        return numOfPedestrians;
    }

    public void setNumOfPedestrians(int numOfPedestrians) {
        this.numOfPedestrians = numOfPedestrians;
    }
}
