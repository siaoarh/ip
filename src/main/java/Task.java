public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    protected String statusIcon() {
        return isDone ? "X" : " ";
    }

    protected abstract String typeIcon();

    @Override
    public String toString() {
        return "[" + typeIcon() + "][" + statusIcon() + "] " + description;
    }
}
