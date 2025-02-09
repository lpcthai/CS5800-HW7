import java.util.Iterator;

public class SearchMessageByUser implements Iterator<Message> {
    private Iterator<Message> messageIterator;
    private User userToSearchWith;

    public SearchMessageByUser(Iterator<Message> messageIterator, User userToSearchWith) {
        this.messageIterator = messageIterator;
        this.userToSearchWith = userToSearchWith;
    }

    @Override
    public boolean hasNext() {
        while (messageIterator.hasNext()) {
            Message message = messageIterator.next();
            if (message.getSender().equals(userToSearchWith) ||
                    message.getReceivers().contains(userToSearchWith)) {
                messageIterator = userToSearchWith.iterator();
                return true;
            }
        }
        return false;
    }

    @Override
    public Message next() {
        while (messageIterator.hasNext()) {
            Message message = messageIterator.next();
            if (message.getSender().equals(userToSearchWith) ||
                    message.getReceivers().contains(userToSearchWith)) {
                return message;
            }
        }
        return null;
    }

    public void remove() {
        messageIterator.remove();
    }
}