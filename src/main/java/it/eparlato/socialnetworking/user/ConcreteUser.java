package it.eparlato.socialnetworking.user;

import it.eparlato.socialnetworking.message.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConcreteUser implements User {
    private final String username;
    private LinkedList<Message> messages = new LinkedList<Message>();
    private LinkedList<User> followedUsers = new LinkedList<User>();

    public ConcreteUser(String username) {
        this.username = username;
    }

    public void publish(String message, long timeOfPublishing) {
        messages.push(new Message(message, timeOfPublishing, username));
    }

    @Override
    public List<Message> getTimeline() {
        return messages;
    }

    @Override
    public void follow(User user) {
        followedUsers.push(user);
    }

    @Override
    public List<User> getFollowedUsers() {
        return followedUsers;
    }

    @Override
    public List<Message> wall() {
        List<Message> wallMessages = new ArrayList<Message>();

        wallMessages.addAll(messages);

        for (User user : followedUsers) {
            wallMessages.addAll(user.getTimeline());
        }

        return wallMessages;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ConcreteUser)) {
            return false;
        }

        ConcreteUser that = (ConcreteUser) obj;

        return this.username.equals(that.username);
    }
}
