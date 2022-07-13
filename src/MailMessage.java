import java.util.List;
import java.util.Map;

public class MailMessage extends SortMessage {
    String content;
    public MailMessage(String addressFrom, String addressTo, String content) {
        super(addressFrom, addressTo);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
