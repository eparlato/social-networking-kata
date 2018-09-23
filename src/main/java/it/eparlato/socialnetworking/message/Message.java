package it.eparlato.socialnetworking.message;

import java.util.concurrent.TimeUnit;

public class Message {
    private final String text;
    private final long timeOfPublishing;
    private String username;

    public Message(String text, long timeOfPublishing, String username) {
        this.text = text;
        this.timeOfPublishing = timeOfPublishing;
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public String formatForViewTimelineStartingFrom(long timeOfReading) {
        return text + getElapsedTime(timeOfReading);
    }

    private String getElapsedTime(long now) {
        long elapsedTime = now - timeOfPublishing;
        long elapsedMinutes = getElapsedMinutes(elapsedTime);
        long elapsedSeconds = getElapsedSeconds(elapsedTime);

        return formattedElapsedTime(elapsedMinutes, elapsedSeconds);
    }

    private String formattedElapsedTime(long elapsedMinutes, long elapsedSeconds)  {
        // Returns minutes or seconds, no minutes + seconds (ex. 2 minutes 32 seconds)

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

    private long getElapsedSeconds(long elapsedTime) {
        return TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
    }

    private long getElapsedMinutes(long elapsedTime) {
        return TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
    }

    public String formatForWallStartingFrom(long timeOfWall) {
        return username + " - " + formatForViewTimelineStartingFrom(timeOfWall);
    }

    // FIXME Dependency Inversion Principle violation: the class depends on System
    public void showTimelineOnConsoleStartingFrom(long timeOfReading) {
        System.out.println(formatForViewTimelineStartingFrom(timeOfReading));
    }

    public void showWallOnConsoleStartingFrom(long timeOfWall) {
        System.out.println(formatForWallStartingFrom(timeOfWall));
    }
}
