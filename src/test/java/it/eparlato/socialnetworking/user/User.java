package it.eparlato.socialnetworking.user;

import it.eparlato.socialnetworking.Message;

import java.util.LinkedList;

public interface User {

    void publish(String message, long timeOfPublishing);

    LinkedList<Message> read();

}
