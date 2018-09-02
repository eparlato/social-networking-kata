package it.eparlato.socialnetworking.parser;

import it.eparlato.socialnetworking.command.Command;
import it.eparlato.socialnetworking.command.Publish;
import it.eparlato.socialnetworking.command.Read;
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
        } else {
            return new Read(input, userRepository, applicationClock.currentTimeMillis());
        }
    }
}
