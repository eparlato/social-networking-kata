package it.eparlato.socialnetworking.message;

import java.util.concurrent.TimeUnit;

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

    public String readingText(long now) {
        return text + getElapsedTime(now);
    }

    private String getElapsedTime(long now) {
        long elapsedTime = now - timeOfPublishing;
        long elapsedMinutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
        long elapsedSeconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);

        if (elapsedMinutes > 0) {
            return String.format(" (%d minutes ago)", elapsedMinutes);
        }

        if (elapsedSeconds > 0) {
          return String.format(" (%d seconds ago)", elapsedSeconds);
        }

        return "";
    }
}
