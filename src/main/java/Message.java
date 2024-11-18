import java.util.Date;
import java.util.List;

public class Message {
    private User sender;
    private List<User> receivers;
    private String textMessage;
    private Date timestamp;

    public Message(User sender, List<User> receivers, String textMessage) {
        this.sender = sender;
        this.receivers = receivers;
        this.textMessage = textMessage;
        this.timestamp = new Date();
    }

    public MessageMemento saveToMemento() {
        return new MessageMemento(this);
    }

    public void restoreFromMemento(MessageMemento messageMemento) {
        Message previousMessage = messageMemento.getPreviousMessage();
        this.sender = previousMessage.getSender();
        this.receivers = previousMessage.getReceivers();
        this.textMessage = previousMessage.getTextMessage();
        this.timestamp = previousMessage.getTimestamp();
    }

    public List<User> getReceivers() {
        return receivers;
    }

    public User getSender() {
        return sender;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
       return String.format("" + "%s: Message content: '%s'", timestamp.toString(), textMessage);

    }
}
