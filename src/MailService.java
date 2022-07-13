import java.util.*;
import java.util.function.Consumer;

public class MailService<T> implements Consumer {
    Map<String, List<T>> tempMap = new HashMap<>() {
        @Override
        public List<T> get(Object key) {
            return super.get(key) == null ? (List<T>) Collections.<String>emptyList() : super.get(key);
        }
    };

    @Override
    public void accept(Object o) {
        SortMessage sortMessage = (SortMessage) o;
        tempMap.computeIfAbsent(sortMessage.getTo(), k -> new ArrayList<>()).add((T) sortMessage.getContent());
    }

    public Map<String, List<T>> getMailBox() {
        return tempMap;
    }
}
