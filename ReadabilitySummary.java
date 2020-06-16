package readability;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ReadabilitySummary {

    private final String input;
    private final int words;
    private final String[] sentences;
    private final int syllables;
    private final int pollySyllables;
    private final int characters;
    private final static List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u', 'y');


    public ReadabilitySummary(String input) {
        this.input = input;
        this.sentences = input.split("[.!?]");
        this.words = Arrays.stream(sentences)
                .flatMapToInt(line -> {
                    String trimmed = line.trim();
                    return IntStream.of(trimmed.split(" ").length);
                }).sum();
        this.characters = Arrays.stream(input.split(" "))
                .mapToInt(line -> line.split("")
                        .length).sum();
        int[] syllablesAndPollySyllables = calcSyllables();
        this.syllables = syllablesAndPollySyllables[0];
        this.pollySyllables = syllablesAndPollySyllables[1];
    }

    private int[] calcSyllables(){
        int syllables = 0;
        int polySyllables = 0;
        for (String word : input.split(" ")){
            int finalIndex = 1;

            if(word.substring(word.length()-1).matches("[.!?,]")){
                finalIndex = 2;
            }
            boolean vowelFound = false;
            boolean lastWasVowel = false;
            int syllablesInWord = 0;
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if(c == 'e' && j == word.length()-finalIndex) continue;
                if(vowels.contains(c)){
                    if(!lastWasVowel){
                        syllablesInWord++;
                        syllables++;
                        vowelFound = true;
                        lastWasVowel = true;
                    }

                }
                else{
                    lastWasVowel = false;
                }

            }
            if(syllablesInWord > 2){
                polySyllables++;
            }
            if(!vowelFound) syllables++;
        }
        return new int[]{syllables, polySyllables};
    }

    public int getWords() {
        return words;
    }

    public String[] getSentences() {
        return sentences;
    }

    public int getSyllables() {
        return syllables;
    }

    public int getCharacters() {
        return characters;
    }

    public int getPollySyllables() {
        return pollySyllables;
    }
}
