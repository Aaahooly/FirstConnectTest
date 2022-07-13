package lessons357;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("Pul9");
        stringList.add("Pul91");
        stringList.add("Pul92");
        Map<String, List<String>> testMap = new HashMap<>();
        testMap.put("1",  stringList);
        testMap.get("1").add("2");
        System.out.println(testMap.get("1"));
        testMap.put("2", stringList);
        stringList.stream().map(s -> s + s).collect(Collectors.toList());

        String[] spam = {"Журчит", "Листва"};
        int commentMaxLength = 40;
        SpamAnalyzer spamAnalyzer = new SpamAnalyzer(spam);
        NegativeTextAnalyzer negativeTextAnalyzer = new NegativeTextAnalyzer();
        TooLongTextAnalyzer tooLongTextAnalyzer = new TooLongTextAnalyzer(10);
        TextAnalyzer[] textAnalyzers1 = {
                new SpamAnalyzer(spam),
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(commentMaxLength)
        };


    }

    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        Label ok = Label.OK;
        for (int i = 0; i < analyzers.length; i++) {
            if (ok == analyzers[i].processText(text)) {
            } else {
                ok = analyzers[i].processText(text);
            }
        }
        return ok;
    }
}

class dog {

}
