package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.user.User;
import it.eparlato.socialnetworking.user.UserRepository;

public class Publish implements Command {
    private final UserRepository userRepository;
    private String username;
    private String message;

    public Publish(String username, String message, UserRepository userRepository) {
        this.username = username;
        this.message = message;
        this.userRepository = userRepository;
    }

    public void execute() {
        User user = userRepository.getUser(username);
        user.post(message);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Publish)) {
            return false;
        }

        Publish that = (Publish) obj;

        if (!(this.username.equals(that.username))) {
            return false;
        }

        if(!(this.message.equals(that.message))) {
            return false;
        }

        return true;
    }
}
