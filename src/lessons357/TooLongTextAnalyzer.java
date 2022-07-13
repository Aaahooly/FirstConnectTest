package lessons357;

public class TooLongTextAnalyzer implements TextAnalyzer {
    private int maxLength;
    public TooLongTextAnalyzer(int maxLength){
        this.maxLength = maxLength;
    }

    @Override
    public Label processText(String text) {
        if(text.length() < this.maxLength){
            return Label.OK;
        }
        return Label.TOO_LONG;
    }
}
