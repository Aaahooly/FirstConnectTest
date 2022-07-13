import java.util.List;
import java.util.Map;

public abstract class SortMessage <T> {
    String addressFrom;
    String addressTo;

    public SortMessage(String addressFrom,String addressTo){
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
    }

    public String getFrom() {
        return addressFrom;
    }

    public String getTo() {
        return addressTo;
    }
    public abstract T getContent();
}
