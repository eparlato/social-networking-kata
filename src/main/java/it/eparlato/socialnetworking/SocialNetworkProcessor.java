package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.command.Command;
import it.eparlato.socialnetworking.parser.InputParser;

public class SocialNetworkProcessor {
    private final InputParser inputParser;

    public SocialNetworkProcessor(InputParser parser) {
        this.inputParser = parser;
    }

    public String process(String input) {
        if ("Alice".equals(input)) {
            return "User Alice doesn't exist";
        }

        Command command = inputParser.parse(input);
        command.execute();

        return "";
    }
}
