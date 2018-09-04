package it.eparlato.socialnetworking.user;

import it.eparlato.socialnetworking.message.Message;

import java.util.List;

public interface User {
    // TODO: I'm not sure ths is the proper name for this object

    void publish(String message, long timeOfPublishing);

    List<Message> getTimeline();

    void follow(User user);

    List<User> getFollowedUsers();

    List<Message> wall();
}
