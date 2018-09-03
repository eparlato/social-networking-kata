package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.user.User;
import it.eparlato.socialnetworking.user.repository.UserRepository;

public class Wall implements Command {
    private final String username;
    private final UserRepository userRepository;
    private final long timeOfWall;

    public Wall(String username, UserRepository userRepository, long timeOfWall) {
        this.username = username;
        this.userRepository = userRepository;
        this.timeOfWall = timeOfWall;
    }

    @Override
    public void execute() {
        User user = userRepository.getUser(username);
        user.wall();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Wall)) {
            return false;
        }

        Wall that = (Wall) obj;

        if (!(this.username.equals(that.username))) {
            return false;
        }

        if (!(this.timeOfWall == timeOfWall)) {
            return false;
        }

        return true;
    }
}
