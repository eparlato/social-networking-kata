package it.eparlato.socialnetworking;

public class Message {
    private final String text;
    private final long timeOfPublishing;

    public Message(String text, long timeOfPublishing) {
        this.text = text;
        this.timeOfPublishing = timeOfPublishing;
    }

    public String getText() {
        return text;
    }
}
