public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    protected String typeIcon() {
        return "D";
    }

    @Override
    public String toStorageString(){
        return typeIcon() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[" + typeIcon() + "][" + statusIcon() + "] "
                + description + " (by: " + by + ")";
    }
}
