package trafficlight;

public interface State {
    public String reportState();

    public void change();
}
