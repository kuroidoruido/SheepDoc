package nf.fr.k49.sheepdoc.state;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class State {
    public Optional<String> templatePath = Optional.empty();
    public Optional<String> outputDocumentPath = Optional.empty();
    public List<String> placeholders = List.of();
    public Map<String,String> placeholderValues = Map.of();
}