package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.command.Command;
import it.eparlato.socialnetworking.command.CommandParser;

public class CommandParserDummy implements CommandParser {
    public Command parse(String command) {
        return null;
    }
}
