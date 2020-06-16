package readability;

import readability.methods.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static Map<Long, Integer> ages = Map.ofEntries(
            Map.entry(1L, 6),
            Map.entry(2L, 7),
            Map.entry(3L, 9),
            Map.entry(4L, 10),
            Map.entry(5L, 11),
            Map.entry(6L, 12),
            Map.entry(7L, 13),
            Map.entry(8L, 14),
            Map.entry(9L, 15),
            Map.entry(10L, 16),
            Map.entry(11L, 17),
            Map.entry(12L, 18),
            Map.entry(13L, 24),
            Map.entry(14L, 25));

    public static void main(String[] args) {


        String text = "";

        try {
            byte[] allBytes = Files.readAllBytes(Paths.get(args[0]));
            text = new String(allBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }


        ReadabilitySummary summary = new ReadabilitySummary(text);
        ReadabilityScore fleschKincaid = new FleschKincaid(summary);
        ReadabilityScore smogIndex = new SMOGIndex(summary);
        ReadabilityScore colemanLiau = new ColemanLiauIndex(summary);
        ReadabilityScore automatedReadability = new AutomatedReadabilityIndex(summary);

        System.out.println("The text is: ");
        System.out.println(text + "\n");
        System.out.println("Words: " + summary.getWords());
        System.out.println("Sentences: " + summary.getSentences().length);
        System.out.println("Characters: " + summary.getCharacters());
        System.out.println("Syllables: " + summary.getSyllables());
        System.out.println("Polysyllables: " + summary.getPollySyllables());
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Scanner scanner = new Scanner(System.in);
        String readabilityMethod = scanner.nextLine();
        System.out.println();

        switch (readabilityMethod) {
            case "ARI":
                System.out.println(automatedReadability);
                break;
            case "FK":
                System.out.println(fleschKincaid);
                break;
            case "SMOG":
                System.out.println(smogIndex);
                break;
            case "CL":
                System.out.println(colemanLiau);
                break;
            case "all":
                System.out.println(automatedReadability);
                System.out.println(fleschKincaid);
                System.out.println(smogIndex);
                System.out.println(colemanLiau);

                int averageAge = automatedReadability.getAge() + fleschKincaid.getAge() + smogIndex.getAge() + colemanLiau.getAge();
                double avg = averageAge / 4f;
                System.out.println();
                System.out.format("This text should be understood in average by %.2f year olds", avg);
                break;
        }


    }
}
