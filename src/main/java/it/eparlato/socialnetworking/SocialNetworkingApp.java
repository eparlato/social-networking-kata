package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.parser.ConcreteInputParser;
import it.eparlato.socialnetworking.parser.InputParser;
import it.eparlato.socialnetworking.time.SystemApplicationClock;
import it.eparlato.socialnetworking.user.repository.InMemoryUserRepository;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class SocialNetworkingApp {
    private final InputStream inputStream;
    private final PrintStream outputStream;
    private final InputParser inputParser;

    private SocialNetworkProcessor socialNetworkProcessor;

    public SocialNetworkingApp(InputStream inputStream, PrintStream outputStream, InputParser inputParser) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.inputParser = inputParser;
    }

    public void run() {
        Scanner scanner = new Scanner(inputStream);
        socialNetworkProcessor = new SocialNetworkProcessor(inputParser);
        String result;

        while (scanner.hasNext()) {
            result = socialNetworkProcessor.process(scanner.nextLine());

            outputStream.println(result);
        }
    }

    public static void main(String[] args) {
        SocialNetworkingApp app = new SocialNetworkingApp(System.in, System.out,
                new ConcreteInputParser(new InMemoryUserRepository(), new SystemApplicationClock()));
        app.run();
    }
}
