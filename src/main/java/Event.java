public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }


    @Override
    protected String typeIcon() {
        return "E";
    }

    @Override
    public String toStorageString() {
        return typeIcon() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[" + typeIcon() + "][" + statusIcon() + "] "
                + description + " (from: " + from + " to: " + to + ")";
    }
}
