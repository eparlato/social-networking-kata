package it.eparlato.socialnetworking.parser;

import it.eparlato.socialnetworking.command.Command;
import it.eparlato.socialnetworking.command.Publish;
import it.eparlato.socialnetworking.command.ViewTimeline;
import it.eparlato.socialnetworking.time.TweakedApplicationClock;
import it.eparlato.socialnetworking.user.repository.DummyUserRepository;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class InputParserShould {
    Command expectedCommand;
    InputParser parser;
    String input;

    @Before
    public void setup() {
        parser = new ConcreteInputParser(new DummyUserRepository(), new TweakedApplicationClock(System.currentTimeMillis()));
    }

    @Test
    public void return_a_Publish_command_if_the_input_is_a_username_followed_by_a_message() {
        input = "Bob -> Damn! We lost!";
        expectedCommand = new Publish("Bob", "Damn! We lost!", new DummyUserRepository(), 0);

        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void return_a_ViewTimeline_command_if_the_is_input_a_user_name() {
        input = "Alice";
        expectedCommand = new ViewTimeline("Alice", new DummyUserRepository(), 0);

        assertEquals(expectedCommand, parser.parse(input));
    }
}
