package searchautocomplete.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private final Map<Character, TrieNode> children;
    private boolean isWord;
    private int frequency;
    private String word;

    public TrieNode() {
        this.children = new HashMap<>();
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public String getWord() {
        return word;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void markAsWord() {
        isWord = true;
    }

    public void incrementFrequency() {
        frequency++;
    }

    public int getFrequency() {
        return frequency;
    }
}
