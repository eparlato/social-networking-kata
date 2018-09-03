package it.eparlato.socialnetworking.user;

import it.eparlato.socialnetworking.message.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConcreteUser implements User {
    private final String userName;
    private LinkedList<Message> messages = new LinkedList<Message>();
    private LinkedList<User> followedUsers = new LinkedList<User>();

    public ConcreteUser(String userName) {
        this.userName = userName;
    }

    public void publish(String message, long timeOfPublishing) {
        messages.push(new Message(message, timeOfPublishing, userName));
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

        return this.userName.equals(that.userName);
    }
}
