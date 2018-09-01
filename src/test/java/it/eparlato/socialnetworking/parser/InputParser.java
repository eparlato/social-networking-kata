package it.eparlato.socialnetworking.parser;

import it.eparlato.socialnetworking.command.Command;

public interface InputParser {
    Command parse(String command);
}
