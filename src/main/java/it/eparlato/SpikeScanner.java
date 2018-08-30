package it.eparlato;

import java.util.Scanner;

public class SpikeScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            System.out.println("You wrote: " + input);

            if("q".equalsIgnoreCase(input)) {
                break;
            }
        }
    }
}
