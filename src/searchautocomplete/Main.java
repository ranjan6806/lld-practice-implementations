package searchautocomplete;

import searchautocomplete.builder.AutoCompleteSystemBuilder;
import searchautocomplete.core.AutoCompleteSystem;
import searchautocomplete.strategy.FrequencyBasedRankingStrategy;

public class Main {
    public static void main(String[] args) {
        AutoCompleteSystem system = new AutoCompleteSystemBuilder()
                .withRankingStrategy(new FrequencyBasedRankingStrategy())
                .withMaxSuggestions(10)
                .build();

        system.addWord("apple");
        system.addWord("apple");
        system.addWord("application");
        system.addWord("app");
        system.addWord("app");
        system.addWord("app");

        System.out.println(system.getSuggestions("ap"));
    }
}
