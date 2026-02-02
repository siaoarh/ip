public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String typeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + typeIcon() + "][" + statusIcon() + "] "
                + description + " (by: " + by + ")";
    }
}
