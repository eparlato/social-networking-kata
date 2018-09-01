package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.command.Command;

public interface CommandParser {
    Command parse(String command);
}
