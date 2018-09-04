package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.message.Message;
import it.eparlato.socialnetworking.user.User;

import java.util.List;

public class ViewTimeline implements Command {
    private final long timeOfReading;
    private final User user;

    public ViewTimeline(User user, long timeOfReading) {
        this.user = user;
        this.timeOfReading = timeOfReading;
    }

    @Override
    public void execute() {
        List<Message> messages = user.getTimeline();

        for (Message message : messages) {
            message.showTimelineOnConsoleStartingFrom(timeOfReading);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ViewTimeline)) {
            return false;
        }

        ViewTimeline that = (ViewTimeline) obj;

        if (!(this.user.equals(that.user))) {
            return false;
        }

        if (!(this.timeOfReading == that.timeOfReading)) {
            return false;
        }

        return true;
    }
}
