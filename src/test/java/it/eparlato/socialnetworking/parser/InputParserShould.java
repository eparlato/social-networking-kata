package it.eparlato.socialnetworking.parser;

import it.eparlato.socialnetworking.command.Command;
import it.eparlato.socialnetworking.command.Publish;
import it.eparlato.socialnetworking.command.Read;
import it.eparlato.socialnetworking.time.TweakedApplicationClock;
import it.eparlato.socialnetworking.user.DummyUserRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.MonthDay;

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
    public void return_a_publish_command_if_the_input_is_publish_a_message() {
        input = "Bob -> Damn! We lost!";
        expectedCommand = new Publish("Bob", "Damn! We lost!", new DummyUserRepository(), 0);

        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void return_a_read_command_if_the_input_is_read_a_user() {
        input = "Alice";
        expectedCommand = new Read("Alice", new DummyUserRepository(), 0);

        assertEquals(expectedCommand, parser.parse(input));
    }
}
