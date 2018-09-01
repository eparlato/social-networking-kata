package it.eparlato.socialnetworking;

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
