import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class MessageTest {

    @Test
    void testMessageInitialization() {
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        String text = "Hello, Chloe!";
        Message message = new Message(sender, List.of(receiver), text);
        assertEquals(sender, message.getSender());
        assertEquals(1, message.getReceivers().size());
        assertEquals(receiver, message.getReceivers().get(0));
        assertEquals(text, message.getTextMessage());
        assertNotNull(message.getTimestamp(), "The message timestamp from group chat");
    }

    @Test
    void testSaveToMemento() {
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message = new Message(sender, List.of(receiver), "Hello, Chloe!");
        MessageMemento memento = message.saveToMemento();
        Message restoredMessage = memento.getPreviousMessage();
        assertEquals(message.toString(), restoredMessage.toString(), " Saved message in Memento from group chat.");
    }

    @Test
    void testRestoreFromMemento() {
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message = new Message(sender, List.of(receiver), "Hello, Larry!");
        MessageMemento memento = message.saveToMemento();
        message.restoreFromMemento(memento);
        Message restoredMessage = memento.getPreviousMessage();
        assertEquals(message.toString(), restoredMessage.toString(), "Restored messages from themessage saved in Memento.");
    }

    @Test
    void testMessageTimestamp() {
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message = new Message(sender, List.of(receiver), "Hello, yall!");
        Date timestamp = message.getTimestamp();
        assertNotNull(timestamp, "Timestamp should not be null.");
        assertTrue(new Date().after(timestamp), "Timestamp should be initialized to the creation time.");
    }
}
