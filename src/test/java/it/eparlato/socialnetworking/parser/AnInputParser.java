package it.eparlato.socialnetworking.parser;

import it.eparlato.socialnetworking.command.Command;
import it.eparlato.socialnetworking.command.Publish;
import it.eparlato.socialnetworking.user.UserRepositoryDummy;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AnInputParser {
    @Test
    public void should_return_a_publish_command_if_the_input_is_publish_a_message() {
        Command expectedCommand = new Publish("Bob", "Damn! We lost!", new UserRepositoryDummy());
        InputParser parser = new InputParserConcrete(new UserRepositoryDummy());
        String input = "Bob -> Damn! We lost!";

        assertEquals(expectedCommand, parser.parse(input));
    }
}
