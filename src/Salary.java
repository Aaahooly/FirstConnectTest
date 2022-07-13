import java.util.List;
import java.util.Map;

public class Salary extends SortMessage {
    Integer content;
    public Salary(String addressFrom, String addressTo, Integer content) {
        super(addressFrom, addressTo);
        this.content = content;
    }

    @Override
    public Integer getContent() {
        return content;
    }
}
