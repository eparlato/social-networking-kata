package it.eparlato.socialnetworking.user;

import it.eparlato.socialnetworking.message.Message;

import java.util.LinkedList;
import java.util.List;

public class ConcreteUser implements User {
    private final String userName;
    private LinkedList<Message> messages = new LinkedList<>();

    public ConcreteUser(String userName) {
        this.userName = userName;
    }

    public void publish(String message, long timeOfPublishing) {
        messages.push(new Message(message, timeOfPublishing));
    }

    @Override
    public List<Message> getTimeline() {
        return messages;
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
