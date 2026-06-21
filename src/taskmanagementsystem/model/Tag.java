package taskmanagementsystem.model;

import java.util.Map;

public class Tag {
    private final String name;
    private Map<String, String> metadata;

    public Tag(String name) {
        this.name = name;
    }

    private void addMetadata(String key, String value) {
        metadata.put(key, value);
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }
}
