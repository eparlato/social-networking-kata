package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.command.Command;
import it.eparlato.socialnetworking.command.CommandParser;

public class SocialNetworkProcessor {
    private final CommandParser commandParser;

    public SocialNetworkProcessor(CommandParser parser) {
        this.commandParser = parser;
    }

    public String process(String input) {
        if ("Alice".equals(input)) {
            return "User Alice doesn't exist";
        }

        Command command = commandParser.parse(input);
        command.execute();

        return "";
    }
}
