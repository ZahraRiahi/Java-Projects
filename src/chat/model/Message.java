package chat.model;

public class Message {
    private final int id;
    private final String text;

    public Message(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() { return id; }
    public String getText() { return text; }

    @Override
    public String toString() {
        return "Message#" + id + " [" + text + "]";
    }
}