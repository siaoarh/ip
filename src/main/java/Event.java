public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    protected String typeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + typeIcon() + "][" + statusIcon() + "] "
                + description + " (from: " + from + " to: " + to + ")";
    }
}
