package lessons357;
// Этот класс должен анализировать текст по вхордным ключевым словам
//

public class SpamAnalyzer extends KeywordAnalyzer  {
    private String[] keywords;

    public SpamAnalyzer(String[] keywords){
        this.keywords = keywords;
    }

    @Override
    public String[] getKeywords() { // будет возвращать набор ключевых слов
        return this.keywords;
    }

    @Override
    public Label getLabel() { // возврат строки с положительными метками(смайликами) нужен для класса negativetextanalyzer
        return Label.SPAM;
    }


}
