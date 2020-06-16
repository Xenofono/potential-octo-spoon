package readability.methods;

import readability.Main;
import readability.ReadabilitySummary;

public class AutomatedReadabilityIndex implements ReadabilityScore {

    private final String methodName;
    final private ReadabilitySummary summary;
    private final int age;
    private final double score;

    public AutomatedReadabilityIndex(ReadabilitySummary summary) {
        this.methodName = "Automated Readability Index:";
        this.summary = summary;
        this.score = calculateScore();
        this.age = Main.ages.get(Math.round(this.score));
    }

    @Override
    public double calculateScore( ) {
        double chars = this.summary.getCharacters();
        double words = this.summary.getWords();
        double sentences = this.summary.getSentences().length;
        return 4.71 * (chars / words) + 0.5 * (words / sentences) - 21.43;
    }

    @Override
    public String toString() {
        String ageString = age == 25 ? "24+" : String.valueOf(age);
        return String.format("%s %.2f (about %s year olds)", this.methodName, this.calculateScore(), ageString);
    }

    public int getAge() {
        return age;
    }
}
