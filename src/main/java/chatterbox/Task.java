package chatterbox;

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

    public void setDone(boolean done) {
        isDone = done;
    }

    protected String statusIcon() {
        return isDone ? "X" : " ";
    }

    protected abstract String typeIcon();

    public String toStorageString() {
        return typeIcon() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + typeIcon() + "][" + statusIcon() + "] " + description;
    }
}
