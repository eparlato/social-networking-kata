package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.message.Message;
import it.eparlato.socialnetworking.user.InMemoryUser;
import it.eparlato.socialnetworking.user.User;
import org.junit.Test;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class UserBehaviour {
    final long ANY_TIME_VALUE = 0;

    @Test
    public void a_user_should_store_a_message_when_it_is_published() {
        User user = new InMemoryUser("Bob");

        user.publish("Damn! We lost!", ANY_TIME_VALUE);
        user.publish("Good game though.", ANY_TIME_VALUE);
        user.publish("Next time we will win!", ANY_TIME_VALUE);

        assertEquals(3, user.getTimeline().size());
    }

    @Test
    public void a_user_should_return_a_LIFO_list_of_messages_when_the_timeline_is_requested() {
        String firstMessage = "Today it is raining.";
        String secondMessage = "Yesterday it was raining.";
        String thirdMessage = "What about tomorrow?";

        User user = new InMemoryUser("Alice");

        user.publish(firstMessage, ANY_TIME_VALUE);
        user.publish(secondMessage, ANY_TIME_VALUE);
        user.publish(thirdMessage, ANY_TIME_VALUE);

        List<Message> messages = user.getTimeline();

        assertEquals(thirdMessage, messages.get(0).getText());
        assertEquals(secondMessage, messages.get(1).getText());
        assertEquals(firstMessage, messages.get(2).getText());
    }

    @Test
    public void a_user_should_add_a_user_on_top_of_his_followed_list_when_it_follows_a_user() {
        User bob = new InMemoryUser("Bob");;
        User alice = new InMemoryUser("Alice");

        User charlie = new InMemoryUser("Charlie");

        charlie.follow(alice);
        charlie.follow(bob);

        List<User> followedUsers = charlie.getFollowedUsers();

        assertEquals(bob, followedUsers.get(0));
        assertEquals(alice, followedUsers.get(1));
    }

    @Test
    public void a_user_should_return_a_list_of_messages_of_followed_users_after_his_timeline() {
        User alice = new InMemoryUser("Alice");
        User bob = new InMemoryUser("Bob");
        User charlie = new InMemoryUser("Charlie");

        alice.publish("It's raining today.", ANY_TIME_VALUE);
        bob.publish("I wish I got my umbrella", ANY_TIME_VALUE);
        bob.publish("Tomorrow will be sunny I hope", ANY_TIME_VALUE);
        charlie.publish("Someone want to buy an umbrella?", ANY_TIME_VALUE);
        charlie.follow(bob);
        charlie.follow(alice);

        List<Message> messages = charlie.wall();

        assertEquals("Someone want to buy an umbrella?", messages.get(0).getText());
        assertEquals("It's raining today.", messages.get(1).getText());
        assertEquals("Tomorrow will be sunny I hope", messages.get(2).getText());
        assertEquals("I wish I got my umbrella", messages.get(3).getText());
    }
}
