package it.eparlato.socialnetworking;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SocialNetworkProcessorTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void should_handle_a_publish_command() {
        final String command = "Alice -> I love the weather today";
        final CommandParser parser = context.mock(CommandParser.class);
        final UserRepository userRepository = context.mock(UserRepository.class);
        final User userAlice = context.mock(User.class);

        SocialNetworkProcessor socialNetworkProcessor = new SocialNetworkProcessor(parser);

        final Command post = new Publish("Alice", "I love the weather today", userRepository);

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
}
