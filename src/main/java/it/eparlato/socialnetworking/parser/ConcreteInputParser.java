package it.eparlato.socialnetworking.parser;

import it.eparlato.socialnetworking.command.*;
import it.eparlato.socialnetworking.time.ApplicationClock;
import it.eparlato.socialnetworking.user.repository.UserRepository;

public class ConcreteInputParser implements InputParser {
    private final ApplicationClock applicationClock;
    private final UserRepository userRepository;

    public ConcreteInputParser(UserRepository userRepository, ApplicationClock applicationClock) {
        this.userRepository = userRepository;
        this.applicationClock = applicationClock;
    }

    public Command parse(String input) {
        if (input.contains("->")) {
            String[] inputSplit = input.split("->");
            String userName = inputSplit[0].trim();
            String message = inputSplit[1].trim();

            return new Publish(userName, message, userRepository, applicationClock.currentTimeMillis());
        }

        if(input.contains("follows")) {
            String[] inputSplit = input.split("follows");
            String follower = inputSplit[0].trim();
            String followed = inputSplit[1].trim();

            return new Follow(follower, followed, userRepository);
        }

        if(input.contains("wall")) {
            String username = input.split("wall")[0].trim();

            return new Wall(username, userRepository, applicationClock.currentTimeMillis());
        }

        return new ViewTimeline(input, userRepository, applicationClock.currentTimeMillis());
    }
}
