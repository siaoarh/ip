package chatterbox;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    protected String typeIcon() {
        return "T";
    }
}
