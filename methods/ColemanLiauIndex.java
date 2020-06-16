package readability.methods;

import readability.Main;
import readability.ReadabilitySummary;

public class ColemanLiauIndex implements ReadabilityScore {

    final private ReadabilitySummary summary;
    final private String methodName;
    private final int age;
    private final double score;


    public ColemanLiauIndex(ReadabilitySummary summary) {
        this.summary = summary;
        this.methodName = "Coleman–Liau index:";
        this.score = calculateScore();
        this.age = Main.ages.get(Math.round(this.score));

    }

    @Override
    public double calculateScore() {
        double chars = this.summary.getCharacters();
        double words = this.summary.getWords();
        double sentences = this.summary.getSentences().length;
        double l = chars / words * 100;
        double s = sentences / words * 100;
        return 0.0588 * l - 0.296 * s - 15.8;
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