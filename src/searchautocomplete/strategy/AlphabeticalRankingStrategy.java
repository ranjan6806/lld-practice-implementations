package searchautocomplete.strategy;

import searchautocomplete.model.Suggestion;

import java.util.Comparator;
import java.util.List;

public class AlphabeticalRankingStrategy implements RankingStrategy {
    public List<Suggestion> rank(List<Suggestion> suggestions) {
        suggestions.sort(Comparator.comparing(Suggestion::getWord));
        return suggestions;
    }
}
