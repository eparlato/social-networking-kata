package it.eparlato.socialnetworking.user;

import it.eparlato.socialnetworking.Message;
import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

public class UserShould {
    @Test
    public void store_a_message_when_it_is_published() {
        User user = new ConcreteUser("Bob");

        user.post("Damn! We lost!", System.currentTimeMillis());
        user.post("Good game though.", System.currentTimeMillis());
        user.post("Next time we will win!", System.currentTimeMillis());

        assertEquals(3, user.read().size());
    }

    @Test
    public void return_a_LIFO_list_of_messages_when_read_is_requested() {
        String firstMessage = "Today it is raining.";
        String secondMessage = "Yesterday it was raining.";
        String thirdMessage = "What about tomorrow?";

        User user = new ConcreteUser("Alice");

        user.post(firstMessage, System.currentTimeMillis());
        user.post(secondMessage, System.currentTimeMillis());
        user.post(thirdMessage, System.currentTimeMillis());

        LinkedList<Message> messages = user.read();

        assertEquals(thirdMessage, messages.pop().getText());
        assertEquals(secondMessage, messages.pop().getText());
        assertEquals(firstMessage, messages.pop().getText());
    }
}
