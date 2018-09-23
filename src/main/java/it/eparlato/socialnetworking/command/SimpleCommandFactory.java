package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.time.ApplicationClock;
import it.eparlato.socialnetworking.user.repository.UserRepository;

public class SimpleCommandFactory implements CommandFactory {
    private final UserRepository userRepository;
    private final ApplicationClock applicationClock;

    public SimpleCommandFactory(UserRepository userRepository, ApplicationClock applicationClock) {
        this.userRepository = userRepository;
        this.applicationClock = applicationClock;
    }

    @Override
    public Command build(String username, String command, String commandParameter) {
        if ("->".equals(command)) {
            return new Publish(userRepository.getUser(username), commandParameter, applicationClock.currentTimeMillis());
        }

        if ("follows".equals(command)) {
            return new Follow(userRepository.getUser(username), userRepository.getUser(commandParameter));
        }

        if ("wall".equals(command)) {
            return new Wall(userRepository.getUser(username), applicationClock.currentTimeMillis());
        }

        return new ViewTimeline(userRepository.getUser(username), applicationClock.currentTimeMillis());
    }
}
