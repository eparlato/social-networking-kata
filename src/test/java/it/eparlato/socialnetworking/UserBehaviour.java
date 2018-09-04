package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.message.Message;
import it.eparlato.socialnetworking.user.ConcreteUser;
import it.eparlato.socialnetworking.user.User;
import org.junit.Test;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class UserBehaviour {
    @Test
    public void a_user_should_store_a_message_when_it_is_published() {
        User user = new ConcreteUser("Bob");

        user.publish("Damn! We lost!", System.currentTimeMillis());
        user.publish("Good game though.", System.currentTimeMillis());
        user.publish("Next time we will win!", System.currentTimeMillis());

        assertEquals(3, user.getTimeline().size());
    }

    @Test
    public void a_user_should_return_a_LIFO_list_of_messages_when_the_timeline_is_requested() {
        String firstMessage = "Today it is raining.";
        String secondMessage = "Yesterday it was raining.";
        String thirdMessage = "What about tomorrow?";

        User user = new ConcreteUser("Alice");

        user.publish(firstMessage, System.currentTimeMillis());
        user.publish(secondMessage, System.currentTimeMillis());
        user.publish(thirdMessage, System.currentTimeMillis());

        List<Message> messages = user.getTimeline();

        assertEquals(thirdMessage, messages.get(0).getText());
        assertEquals(secondMessage, messages.get(1).getText());
        assertEquals(firstMessage, messages.get(2).getText());
    }

    @Test
    public void a_user_should_add_a_user_on_top_of_his_followed_list_when_it_follows_a_user() {
        User bob = new ConcreteUser("Bob");;
        User alice = new ConcreteUser("Alice");

        User charlie = new ConcreteUser("Charlie");

        charlie.follow(alice);
        charlie.follow(bob);

        List<User> followedUsers = charlie.getFollowedUsers();

        assertEquals(bob, followedUsers.get(0));
        assertEquals(alice, followedUsers.get(1));
    }

    @Test
    public void a_user_should_return_a_list_of_messages_of_followed_users_after_his_timeline() {
        User alice = new ConcreteUser("Alice");
        User bob = new ConcreteUser("Bob");
        User charlie = new ConcreteUser("Charlie");

        alice.publish("It's raining today.", System.currentTimeMillis());
        bob.publish("I wish I got my umbrella", System.currentTimeMillis());
        bob.publish("Tomorrow will be sunny I hope", System.currentTimeMillis());
        charlie.publish("Someone want to buy an umbrella?", System.currentTimeMillis());
        charlie.follow(bob);
        charlie.follow(alice);

        List<Message> messages = charlie.wall();

        assertEquals("Someone want to buy an umbrella?", messages.get(0).getText());
        assertEquals("It's raining today.", messages.get(1).getText());
        assertEquals("Tomorrow will be sunny I hope", messages.get(2).getText());
        assertEquals("I wish I got my umbrella", messages.get(3).getText());
    }
}
