package it.eparlato.socialnetworking.command;

import it.eparlato.socialnetworking.user.User;
import it.eparlato.socialnetworking.user.repository.UserRepository;

public class Follow implements Command {
    private final String follower;
    private final String followed;
    private final UserRepository userRepository;

    public Follow(String follower, String followed, UserRepository userRepository) {
        this.follower = follower;
        this.followed = followed;
        this.userRepository = userRepository;
    }

    @Override
    public void execute() {
        User followerUser = userRepository.getUser(follower);
        User followedUser = userRepository.getUser(followed);

        followerUser.follow(followedUser);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Follow)) {
            return false;
        }

        Follow that = (Follow) obj;

        if(!(this.follower.equals(that.follower))) {
            return false;
        }

        if(!(this.followed.equals(that.followed))) {
            return false;
        }

        return true;
    }
}
