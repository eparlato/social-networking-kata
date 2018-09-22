package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.user.User;

public class Follow implements Command {
    private final User follower;
    private final User followed;

    public Follow(User follower, User followed) {
        this.follower = follower;
        this.followed = followed;
    }

    @Override
    public void execute() {
        follower.follow(followed);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Follow)) {
            return false;
        }

        Follow that = (Follow) obj;

        if (!(this.follower.equals(that.follower))) {
            return false;
        }

        if (!(this.followed.equals(that.followed))) {
            return false;
        }

        return true;
    }
}
