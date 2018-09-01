package it.eparlato.socialnetworking.user;

import it.eparlato.socialnetworking.Message;

import java.util.LinkedList;

public class ConcreteUser implements User {
    private final String userName;

    public ConcreteUser(String userName) {
        this.userName = userName;
    }

    public void post(String message) {
        System.out.println(String.format("User %s posted the message: %s", userName, message));
    }

    @Override
    public LinkedList<Message> read() {
        return new LinkedList<Message>();
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
