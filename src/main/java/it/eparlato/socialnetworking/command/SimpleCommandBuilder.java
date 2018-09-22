package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.time.ApplicationClock;
import it.eparlato.socialnetworking.user.repository.UserRepository;

public class SimpleCommandBuilder implements CommandBuilder {
    private final UserRepository userRepository;
    private final ApplicationClock applicationClock;

    public SimpleCommandBuilder(UserRepository userRepository, ApplicationClock applicationClock) {
        this.userRepository = userRepository;
        this.applicationClock = applicationClock;
    }

    @Override
    public Command build(String username, String command, String commandParameter) {
        if (command == null)
            return new ViewTimeline(userRepository.getUser(username), applicationClock.currentTimeMillis());

        if (command.contains("->")) {
            return new Publish(userRepository.getUser(username), commandParameter, applicationClock.currentTimeMillis());
        }

        if(command.contains("follows")) {
            return new Follow(userRepository.getUser(username), userRepository.getUser(commandParameter));
        }

        if(command.contains("wall")) {
            return new Wall(userRepository.getUser(username), applicationClock.currentTimeMillis());
        }

        return null;
    }
}
