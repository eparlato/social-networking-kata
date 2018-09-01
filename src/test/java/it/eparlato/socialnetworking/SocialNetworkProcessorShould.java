package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.command.Command;
import it.eparlato.socialnetworking.command.Read;
import it.eparlato.socialnetworking.parser.ConcreteInputParser;
import it.eparlato.socialnetworking.parser.InputParser;
import it.eparlato.socialnetworking.command.Publish;
import it.eparlato.socialnetworking.time.TweakedApplicationClock;
import it.eparlato.socialnetworking.user.InMemoryUserRepository;
import it.eparlato.socialnetworking.user.User;
import it.eparlato.socialnetworking.user.UserRepository;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.LinkedList;


public class SocialNetworkProcessorShould {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    // TODO: refactor!

    @Test
    public void execute_a_publish_command_when_the_input_is_a_publish() {
        final String command = "Alice -> I love the weather today";
        final InputParser parser = context.mock(InputParser.class);
        final UserRepository userRepository = context.mock(UserRepository.class);
        final User userAlice = context.mock(User.class);

        SocialNetworkProcessor socialNetworkProcessor = new SocialNetworkProcessor(parser);

        final Command post = new Publish("Alice",
                "I love the weather today",
                userRepository, 0);

        context.checking(new Expectations(){
            {
                oneOf(parser).parse(command);
                will(returnValue(post));

                oneOf(userRepository).getUser("Alice");
                will(returnValue(userAlice));

                oneOf(userAlice).post("I love the weather today");
            }
        });

        socialNetworkProcessor.process("Alice -> I love the weather today");
    }

    @Test
    public void execute_a_read_command_if_the_input_is_a_reading() {

        final InputParser parser = context.mock(InputParser.class);
        final UserRepository userRepository = context.mock(UserRepository.class);
        final User userCharlie = context.mock(User.class);

        SocialNetworkProcessor socialNetworkProcessor = new SocialNetworkProcessor(parser);

        final Command read = new Read("Charlie",
                userRepository,
                0);


        context.checking(new Expectations() {
            {
                oneOf(parser).parse("Charlie");
                will(returnValue(read));

                oneOf(userRepository).getUser("Charlie");
                will(returnValue(userCharlie));

                oneOf(userCharlie).read();
                will(returnValue(new LinkedList<Message>()));
            }
        });

        socialNetworkProcessor.process("Charlie");
    }
}
