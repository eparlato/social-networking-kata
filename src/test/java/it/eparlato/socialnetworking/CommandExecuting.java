package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.command.*;
import it.eparlato.socialnetworking.user.ConcreteUser;
import it.eparlato.socialnetworking.user.User;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CommandExecuting {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    final long time = System.currentTimeMillis();
    final User user = context.mock(User.class);

    @Test
    public void user_should_execute_publish_if_the_command_is_Publish() {
        final String message = "Today is a beautiful day";

        Command publish = new Publish(user, message, time);

        context.checking(new Expectations(){
            {
                oneOf(user).publish(message, time);
            }
        });

        publish.execute();
    }

    @Test
    public void user_should_execute_getTimeline_if_the_command_is_ViewTimeline() {
        Command read = new ViewTimeline(user, time);

        context.checking(new Expectations() {
            {
                oneOf(user).getTimeline();
            }
        });

        read.execute();
    }

    @Test
    public void user_should_execute_follow_if_the_command_is_Follow() {
        final User followedUser = new ConcreteUser("Bob");

        Command follow = new Follow(user, followedUser);

        context.checking(new Expectations() {
            {
                oneOf(user).follow(followedUser);
            }
        });

        follow.execute();
    }

    @Test
    public void user_should_execute_wall_if_the_command_is_Wall() {
        Command wall = new Wall(user, time);

        context.checking(new Expectations() {
            {
                oneOf(user).wall();
            }
        });

        wall.execute();
    }
}
