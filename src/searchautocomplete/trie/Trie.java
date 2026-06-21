package searchautocomplete.trie;

import searchautocomplete.model.Suggestion;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(ch, c -> new TrieNode());
        }

        current.markAsWord();
        current.incrementFrequency();
        current.setWord(word);
    }

    public List<Suggestion> searchByPrefix(String prefix) {
        TrieNode prefixNode = findByPrefixNode(prefix);
        List<Suggestion> suggestions = new ArrayList<>();

        if (prefixNode == null) {
            return suggestions;
        }

        collectSuggestions(prefixNode, suggestions);
        return suggestions;
    }

    private TrieNode findByPrefixNode(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.getChildren().get(ch);
        }
        return current;
    }

    private void collectSuggestions(TrieNode node, List<Suggestion> suggestions) {
        if (node.isWord()) {
            suggestions.add(new Suggestion(node.getWord(), node.getFrequency()));
        }

        for (TrieNode child : node.getChildren().values()) {
            collectSuggestions(child, suggestions);
        }
    }
}
