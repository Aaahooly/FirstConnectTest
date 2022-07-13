package lessons357;

public class NegativeTextAnalyzer extends KeywordAnalyzer {
    private String[] keywords = {":|", "=(", ":("};

    @Override
    public String[] getKeywords() {
        return this.keywords;
    }

    @Override
    public Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}
