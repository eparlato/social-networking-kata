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

    public String formatForViewTimelineStartingFrom(long timeOfReading) {
        return text + getElapsedTime(timeOfReading);
    }

    private String getElapsedTime(long now) {
        long elapsedTime = now - timeOfPublishing;
        long elapsedMinutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
        long elapsedSeconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);

        if (elapsedMinutes > 0) {
            String minutesString = elapsedMinutes == 1 ? "minute" : "minutes";
            return String.format(" (%d %s ago)", elapsedMinutes, minutesString);
        }

        if (elapsedSeconds > 0) {
            String secondsString = elapsedSeconds == 1 ? "second" : "seconds";
            return String.format(" (%d %s ago)", elapsedSeconds, secondsString);
        }

        return "";
    }

    public void showTimelineOnConsoleStartingFrom(long timeOfReading) {
        System.out.println(formatForViewTimelineStartingFrom(timeOfReading));
    }
}
