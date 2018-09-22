package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.command.*;
import it.eparlato.socialnetworking.parser.RegexInputParser;
import it.eparlato.socialnetworking.parser.InputParser;
import it.eparlato.socialnetworking.user.InMemoryUser;
import it.eparlato.socialnetworking.user.repository.InMemoryUserRepository;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class InputParsing {
    Command expectedCommand;
    InputParser parser;
    String input;
    long now;

    @Before
    public void setup() {
        now = System.currentTimeMillis();
        parser = new RegexInputParser(new InMemoryUserRepository(), new TweakedApplicationClock(now));
    }

    @Test
    public void a_Publish_command_should_be_returned_if_the_input_is_a_username_followed_by_a_message() {
        input = "Bob -> Damn! We lost!";
        expectedCommand = new Publish(new InMemoryUser("Bob"), "Damn! We lost!", now);

        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void a_ViewTimeline_command_should_be_returned_if_the_is_input_a_user_name() {
        input = "Alice";
        expectedCommand = new ViewTimeline(new InMemoryUser("Alice"), now);

        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void a_Follow_command_should_be_returned_if_the_input_contains_the_string_follows() {
        input = "Alice follows Charlie";
        expectedCommand = new Follow(new InMemoryUser("Alice"), new InMemoryUser("Charlie"));

        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void a_Wall_command_should_be_returned_if_the_input_contains_the_string_wall() {
        input = "Bob wall";
        expectedCommand = new Wall(new InMemoryUser("Bob"), now);

        assertEquals(expectedCommand, parser.parse(input));
    }
}
