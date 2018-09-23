package it.eparlato.socialnetworking.command;

public interface CommandFactory {
    Command build(String username, String command, String commandParameter);
}
