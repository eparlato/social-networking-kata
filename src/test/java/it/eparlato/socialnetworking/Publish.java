package it.eparlato.socialnetworking;

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
}
