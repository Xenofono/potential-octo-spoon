package readability.methods;

import readability.Main;
import readability.ReadabilitySummary;

public class FleschKincaid implements ReadabilityScore {

    final private ReadabilitySummary summary;
    final private String methodName;
    private final int age;
    private final double score;


    public FleschKincaid(ReadabilitySummary summary) {
        this.summary = summary;
        this.methodName = "Flesch–Kincaid readability tests:";
        this.score = calculateScore();
        this.age = Main.ages.get(Math.round(this.score));

    }

    @Override
    public double calculateScore( ) {
        double wordsSentences = (double)summary.getWords()/summary.getSentences().length;
        double syllablesWords = (double)summary.getSyllables()/summary.getWords();
        return 0.39*wordsSentences+11.8*syllablesWords-15.59;
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
