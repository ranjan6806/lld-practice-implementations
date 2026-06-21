package searchautocomplete.builder;

import searchautocomplete.core.AutoCompleteSystem;
import searchautocomplete.strategy.RankingStrategy;
import searchautocomplete.trie.Trie;

public class AutoCompleteSystemBuilder {
    private RankingStrategy rankingStrategy;
    private int maxSuggestions;

    public AutoCompleteSystemBuilder withRankingStrategy(RankingStrategy strategy) {
        this.rankingStrategy = strategy;
        return this;
    }

    public AutoCompleteSystemBuilder withMaxSuggestions(int maxSuggestions) {
        this.maxSuggestions = maxSuggestions;
        return this;
    }

    public AutoCompleteSystem build() {
        return new AutoCompleteSystem(new Trie(), rankingStrategy, maxSuggestions);
    }
}
