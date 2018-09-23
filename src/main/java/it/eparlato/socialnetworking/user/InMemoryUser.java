package it.eparlato.socialnetworking.user;

import it.eparlato.socialnetworking.message.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryUser implements User {
    private final String username;
    private LinkedList<Message> messages = new LinkedList<Message>();
    private LinkedList<User> followedUsers = new LinkedList<User>();

    public InMemoryUser(String username) {
        this.username = username;
    }

    // FIXME Open Closed Principle violation, Dependency Inversion Principle violation: the class depends on Message.
    // If I wanted to add behaviour to Message (ex. a new parameter in its constructor) I would need to modify this class as well
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

        addFollowedUsersMessagesToWall(followedUsers, wallMessages);

        return wallMessages;
    }

    private void addFollowedUsersMessagesToWall(List<User> followedUsers, List<Message> wallMessages) {
        for (User user : followedUsers) {
            wallMessages.addAll(user.getTimeline());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InMemoryUser)) {
            return false;
        }

        InMemoryUser that = (InMemoryUser) obj;

        return this.username.equals(that.username);
    }
}
