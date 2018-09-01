package it.eparlato.socialnetworking.parser;

import it.eparlato.socialnetworking.command.Command;
import it.eparlato.socialnetworking.command.Publish;
import it.eparlato.socialnetworking.time.TweakedApplicationClock;
import it.eparlato.socialnetworking.user.DummyUserRepository;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class InputParserShould {
    @Test
    public void return_a_publish_command_if_the_input_is_publish_a_message() {
        Command expectedCommand = new Publish("Bob", "Damn! We lost!", new DummyUserRepository(), 0);
        InputParser parser = new ConcreteInputParser(new DummyUserRepository(), new TweakedApplicationClock(System.currentTimeMillis()));
        String input = "Bob -> Damn! We lost!";

        assertEquals(expectedCommand, parser.parse(input));
    }
}
