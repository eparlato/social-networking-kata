package it.eparlato.socialnetworking;

import it.eparlato.socialnetworking.parser.DummyInputParser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class SocialNetworkingApp {
    private final InputStream inputStream;
    private final PrintStream outputStream;
    private SocialNetworkProcessor socialNetworkProcessor;

    public SocialNetworkingApp(InputStream inputStream, PrintStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void run() {
        Scanner scanner = new Scanner(inputStream);
        socialNetworkProcessor = new SocialNetworkProcessor(new DummyInputParser());
        String result;

        while (scanner.hasNext()) {
            result = socialNetworkProcessor.process(scanner.nextLine());

            outputStream.println(result);
        }
    }
}
