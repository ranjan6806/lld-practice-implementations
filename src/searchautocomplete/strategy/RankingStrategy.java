package searchautocomplete.strategy;

import searchautocomplete.model.Suggestion;

import java.util.List;

public interface RankingStrategy {
    List<Suggestion> rank(List<Suggestion> suggestions);
}
