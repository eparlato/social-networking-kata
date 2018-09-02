package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.message.Message;
import it.eparlato.socialnetworking.user.User;
import it.eparlato.socialnetworking.user.repository.UserRepository;
import java.util.List;

public class ViewTimeline implements Command {
    private final String username;
    private final UserRepository userRepository;
    private final long timeOfReading;

    public ViewTimeline(String username, UserRepository userRepository, long timeOfReading) {
        this.username = username;
        this.userRepository = userRepository;
        this.timeOfReading = timeOfReading;
    }

    @Override
    public void execute() {
        User user = userRepository.getUser(username);
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

        if (!(this.username.equals(that.username))) {
            return false;
        }

        if (!(this.timeOfReading == that.timeOfReading)) {
            return false;
        }

        return true;
    }
}
