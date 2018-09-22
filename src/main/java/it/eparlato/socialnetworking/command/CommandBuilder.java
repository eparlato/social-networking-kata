package it.eparlato.socialnetworking.command;

public interface CommandBuilder {
    Command build(String username, String command, String commandParameter);
}
