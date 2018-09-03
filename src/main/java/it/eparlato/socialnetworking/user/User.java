package it.eparlato.socialnetworking.user;

import it.eparlato.socialnetworking.message.Message;

import java.util.List;

public interface User {

    void publish(String message, long timeOfPublishing);

    List<Message> getTimeline();

    void follow(User user);

    List<User> getFollowedUsers();

    void wall();
}
