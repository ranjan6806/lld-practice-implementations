package searchautocomplete.core;

import searchautocomplete.model.Suggestion;
import searchautocomplete.strategy.RankingStrategy;
import searchautocomplete.trie.Trie;

import java.util.List;
import java.util.stream.Collectors;

public class AutoCompleteSystem {
    private final Trie trie;
    private final int maxSuggestions;
    private RankingStrategy rankingStrategy;

    public AutoCompleteSystem(Trie trie, RankingStrategy rankingStrategy, int maxSuggestions) {
        this.trie = trie;
        this.maxSuggestions = maxSuggestions;
        this.rankingStrategy = rankingStrategy;
    }

    public void addWord(String word) {
        word = normalize(word);
        trie.insert(word);
    }

    public List<String> getSuggestions(String prefix) {
        prefix = normalize(prefix);
        List<Suggestion> suggestions = trie.searchByPrefix(prefix);
        suggestions = rankingStrategy.rank(suggestions);

        return suggestions.stream()
                .limit(maxSuggestions)
                .map(Suggestion::getWord)
                .collect(Collectors.toList());
    }

    private String normalize(String input) {
        return input.toLowerCase();
    }
}
