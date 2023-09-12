package trafficlight;

public class TrafficLight {
    private String state = "Red light";
    private String id;

    private int count = 0;

    public TrafficLight(String state, String id) {
        this.state = state;
        this.id = id;
        if (state.equals("Red light")) {
            this.count = 6;
        } else if (state.equals("Green light")) {
            this.count = 4;
        } else if (state.equals("Yellow light")) {
            this.count = 1;
        }
    }

    public void change(int numOfCars, int numOfPedestrians) {
        if (count > 0) {
            count -= 1;
            return;
        }
        int trafficDemand = numOfCars + numOfPedestrians;
        if (state.equals("Red light")) {
            state = "Green light";
            count = trafficDemand > 100 ? 6 : 4;
        } else if (state.equals("Green light")) {
            state = "Yellow light";
            count = 1;
        } else if (state.equals("Yellow light")) {
            state = "Red light";
            count = trafficDemand < 10 ? 10 : 6;
        }
    }

    public int timeRemaining() {
        return count;
    }

    public String reportState() {
        if (state.equals("Red light")) {
            return "Red light";
        } else if (state.equals("Green light")) {
            return "Green light";
        } else if (state.equals("Yellow light")) {
            return "Yellow light";
        }
        return "Oops, unknown light!";
    }
}
