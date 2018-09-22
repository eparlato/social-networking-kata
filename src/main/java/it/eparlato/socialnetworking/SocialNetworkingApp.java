package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.command.SimpleCommandBuilder;
import it.eparlato.socialnetworking.parser.RegexInputParser;
import it.eparlato.socialnetworking.parser.InputParser;
import it.eparlato.socialnetworking.time.SystemApplicationClock;
import it.eparlato.socialnetworking.user.repository.InMemoryUserRepository;

import java.io.InputStream;
import java.util.Scanner;

public class SocialNetworkingApp {
    private final InputStream inputStream;
    private final InputParser inputParser;

    private SocialNetworkProcessor socialNetworkProcessor;

    public SocialNetworkingApp(InputStream inputStream, InputParser inputParser) {
        this.inputStream = inputStream;
        this.inputParser = inputParser;
    }

    public void run() {
        Scanner scanner = new Scanner(inputStream);
        socialNetworkProcessor = new SocialNetworkProcessor(inputParser);

        while (scanner.hasNext()) {
            socialNetworkProcessor.process(scanner.nextLine());
        }
    }

    public static void main(String[] args) {
        SocialNetworkingApp app = new SocialNetworkingApp(System.in,
                new RegexInputParser(new SimpleCommandBuilder(new InMemoryUserRepository(), new SystemApplicationClock())));
        app.run();
    }
}
