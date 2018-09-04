package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.message.Message;
import it.eparlato.socialnetworking.user.User;

import java.util.List;

public class Wall implements Command {
    private final User user;
    private final long timeOfWall;

    public Wall(User user, long timeOfWall) {
        this.user = user;
        this.timeOfWall = timeOfWall;
    }

    @Override
    public void execute() {
        List<Message> messages = user.wall();

        for (Message message : messages) {
            message.showWallOnConsoleStartingFrom(timeOfWall);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Wall)) {
            return false;
        }

        Wall that = (Wall) obj;

        if (!(this.user.equals(that.user))) {
            return false;
        }

        if (!(this.timeOfWall == timeOfWall)) {
            return false;
        }

        return true;
    }
}
