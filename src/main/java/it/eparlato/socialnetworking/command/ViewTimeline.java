package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.message.Message;
import it.eparlato.socialnetworking.user.User;
import it.eparlato.socialnetworking.user.repository.UserRepository;

import java.util.LinkedList;

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
        LinkedList<Message> messages = user.getTimeline();

        for (Message message : messages) {
            message.showTimelineOnConsoleStartingFrom(timeOfReading);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ViewTimeline)) {
            return false;
        }

        ViewTimeline that = (ViewTimeline) obj;

        if(!(this.username.equals(that.username))) {
            return false;
        }

        return true;
    }
}
