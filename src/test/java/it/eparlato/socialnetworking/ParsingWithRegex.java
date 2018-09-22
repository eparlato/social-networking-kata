package it.eparlato.socialnetworking;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParsingWithRegex {

    private Pattern pattern;
    Matcher matcher;
    private String regex = "(\\w*)(\\s(wall|->|follows)(\\s(\\w.*))*)*";

    @Before
    public void setup() {
        pattern = Pattern.compile(regex);
    }

    @Test
    public void regex_should_match_the_viewtimeline_command() {
        assertTrue("Username doesn't match", matches("Alice"));
    }

    @Test
    public void regex_should_match_the_wall_command() {
        assertTrue("Wall command doesn't match", matches("Bob wall"));
    }

    @Test
    public void regex_should_match_the_publish_command() {
        assertTrue("Publish command doesn't match", matches("Ed -> Hi"));
    }

    @Test
    public void regex_should_match_the_follows_command() {
        assertTrue("Follows command doesn't match", matches("Bob follows Ed"));
    }

    @Test
    public void should_be_possible_to_extract_follows_command_sintax_from_regex_groups() {
        matcher = pattern.matcher("Bob follows Charlie");
        matcher.find();

        assertEquals("First group doesn't contain the username", "Bob", matcher.group(1));
        assertEquals("Third group doesn't contain the command", "follows", matcher.group(3));
        assertEquals("Fifth group doesn't contain the command parameter", "Charlie", matcher.group(5));
    }

    @Test
    public void should_be_possible_to_exctract_publish_command_sintax_from_regex_groups() {
        matcher = pattern.matcher("Charlie -> I'm in New York today! Anyone wants to have a coffee?");
        matcher.find();

        assertEquals("First group doesn't contain the username", "Charlie", matcher.group(1));
        assertEquals("Third group doesn't contain the command", "->", matcher.group(3));
        assertEquals("Fifth group doesn't contain the command parameter", "I'm in New York today! Anyone wants to have a coffee?", matcher.group(5));
    }

    private boolean matches(String input) {
        return Pattern.matches(regex, input);
    }
}
