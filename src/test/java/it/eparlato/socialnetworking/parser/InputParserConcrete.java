package it.eparlato.socialnetworking.parser;

import it.eparlato.socialnetworking.command.Command;
import it.eparlato.socialnetworking.command.Publish;
import it.eparlato.socialnetworking.user.UserRepository;

public class InputParserConcrete implements InputParser {
    private UserRepository userRepository;

    public InputParserConcrete(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Command parse(String input) {
        if(input.contains("->")) {
            String[] inputSplit = input.split("->");
            String userName = inputSplit[0].trim();
            String message = inputSplit[1].trim();

            return new Publish(userName, message, userRepository);
        }

        return null;
    }
}
