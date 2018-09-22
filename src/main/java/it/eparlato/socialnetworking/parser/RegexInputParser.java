package it.eparlato.socialnetworking.parser;

import it.eparlato.socialnetworking.command.*;
import it.eparlato.socialnetworking.time.ApplicationClock;
import it.eparlato.socialnetworking.user.repository.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexInputParser implements InputParser {
    private final ApplicationClock applicationClock;
    private final UserRepository userRepository;

    public RegexInputParser(UserRepository userRepository, ApplicationClock applicationClock) {
        this.userRepository = userRepository;
        this.applicationClock = applicationClock;
    }

    public Command parse(String input) {
        Pattern pattern = Pattern.compile("(\\w*)(\\s(wall|->|follows)(\\s(\\w.*))*)*");
        Matcher matcher = pattern.matcher(input);
        matcher.find();

        String username = matcher.group(1);
        String commandParameter = matcher.group(5);

        if (input.contains("->")) {
            return new Publish(userRepository.getUser(username), commandParameter, applicationClock.currentTimeMillis());
        }

        if(input.contains("follows")) {
            return new Follow(userRepository.getUser(username), userRepository.getUser(commandParameter));
        }

        if(input.contains("wall")) {
            return new Wall(userRepository.getUser(username), applicationClock.currentTimeMillis());
        }

        return new ViewTimeline(userRepository.getUser(username), applicationClock.currentTimeMillis());
    }
}
