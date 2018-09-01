package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.user.User;
import it.eparlato.socialnetworking.user.UserRepository;

public class Read implements Command {
    private final String username;
    private final UserRepository userRepository;
    private final long timeOfReading;

    public Read(String username, UserRepository userRepository, long timeOfReading) {
        this.username = username;
        this.userRepository = userRepository;
        this.timeOfReading = timeOfReading;
    }

    @Override
    public void execute() {
        User user = userRepository.getUser(username);
        user.read();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Read)) {
            return false;
        }

        Read that = (Read) obj;

        if(!(this.username.equals(that.username))) {
            return false;
        }

        return true;
    }
}
