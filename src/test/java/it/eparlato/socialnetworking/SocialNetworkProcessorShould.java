package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.command.*;
import it.eparlato.socialnetworking.parser.InputParser;
import it.eparlato.socialnetworking.user.ConcreteUser;
import it.eparlato.socialnetworking.user.User;
import it.eparlato.socialnetworking.user.repository.UserRepository;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;


public class SocialNetworkProcessorShould {
    public static final int IRRELEVANT_TIME = 0;

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    final InputParser parser = context.mock(InputParser.class);
    final UserRepository userRepository = context.mock(UserRepository.class);
    final User user = context.mock(User.class);
    SocialNetworkProcessor socialNetworkProcessor = new SocialNetworkProcessor(parser);

    @Test
    public void execute_a_Publish_command_if_the_input_is_a_username_followed_by_a_message() {
        final String input = "Alice -> I love the weather today";
        final String message = "I love the weather today";

        final Command post = new Publish("Alice",
                message,
                userRepository, IRRELEVANT_TIME);

        context.checking(new Expectations() {
            {
                oneOf(parser).parse(input);
                will(returnValue(post));

                oneOf(userRepository).getUser("Alice");
                will(returnValue(user));

                oneOf(user).publish(message, IRRELEVANT_TIME);
            }
        });

        socialNetworkProcessor.process(input);
    }

    @Test
    public void execute_a_ViewTimeline_command_if_the_input_is_a_username() {
        final String input = "Charlie";

        final Command viewTimeline = new ViewTimeline("Charlie",
                userRepository,
                IRRELEVANT_TIME);

        context.checking(new Expectations() {
            {
                oneOf(parser).parse(input);
                will(returnValue(viewTimeline));

                oneOf(userRepository).getUser("Charlie");
                will(returnValue(user));

                oneOf(user).getTimeline();
            }
        });

        socialNetworkProcessor.process(input);
    }

    @Test
    public void execute_a_Follow_command_if_the_input_contains_the_string_follows() {
        final String input = "Bob follows Alice";

        final Command follow = new Follow("Bob", "Alice", userRepository);
        final User userAlice = new ConcreteUser("Alice");

        context.checking(new Expectations() {
            {
                oneOf(parser).parse(input);
                will(returnValue(follow));

                oneOf(userRepository).getUser("Bob");
                will(returnValue(user));

                allowing(userRepository).getUser("Alice");
                will(returnValue(userAlice));

                oneOf(user).follow(userAlice);
            }
        });

        socialNetworkProcessor.process(input);
    }

    @Test
    public void execute_a_Wall_command_if_the_input_contains_the_string_wall() {
        final String input = "Alice wall";

        final Command wall = new Wall("Alice", userRepository, IRRELEVANT_TIME);

        context.checking(new Expectations(){
            {
                oneOf(parser).parse(input);
                will(returnValue(wall));

                oneOf(userRepository).getUser("Alice");
                will(returnValue(user));

                oneOf(user).wall();
            }
        });

        socialNetworkProcessor.process(input);
    }
}
