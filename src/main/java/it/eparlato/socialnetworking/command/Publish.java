package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.user.User;

public class Publish implements Command {
    private final long timeOfPublishing;
    private final User user;
    private String message;

    public Publish(User user, String message, long timeOfPublishing) {
        this.user = user;
        this.message = message;
        this.timeOfPublishing = timeOfPublishing;
    }

    public void execute() {
        user.publish(message, timeOfPublishing);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Publish)) {
            return false;
        }

        Publish that = (Publish) obj;

        if (!(this.user.equals(that.user))) {
            return false;
        }

        if (!(this.message.equals(that.message))) {
            return false;
        }

        if(!(this.timeOfPublishing == that.timeOfPublishing)) {
            return false;
        }

        return true;
    }
}
