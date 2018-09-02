package it.eparlato.socialnetworking.user;

import it.eparlato.socialnetworking.Message;

import java.util.LinkedList;

public class ConcreteUser implements User {
    private final String userName;
    private LinkedList<Message> messages = new LinkedList<>();

    public ConcreteUser(String userName) {
        this.userName = userName;
    }

    public void post(String message, long timeOfPublishing) {
        messages.push(new Message(message, timeOfPublishing));
    }

    @Override
    public LinkedList<Message> read() {
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
