package it.eparlato.socialnetworking.parser;

import it.eparlato.socialnetworking.command.Command;
import it.eparlato.socialnetworking.command.CommandBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexInputParser implements InputParser {
    private CommandBuilder commandBuilder;

    public RegexInputParser(CommandBuilder commandBuilder) {
        this.commandBuilder = commandBuilder;
    }

    public Command parse(String input) {
        Pattern pattern = Pattern.compile("(\\w*)(\\s(wall|->|follows)(\\s(\\w.*))*)*");
        Matcher matcher = pattern.matcher(input);
        matcher.find();

        String username = matcher.group(1);
        String command = matcher.group(3);
        String commandParameter = matcher.group(5);

        return commandBuilder.build(username, command, commandParameter);
    }
}
