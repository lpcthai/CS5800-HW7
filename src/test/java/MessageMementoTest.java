import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageMementoTest {

    @Test
    void testMessageMementoStoresMessage() {
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);

        Message originalMessage = new Message(sender, List.of(receiver), "Hello, Chloe!");
        MessageMemento memento = new MessageMemento(originalMessage);
        Message restoredMessage = memento.getPreviousMessage();
        assertEquals(originalMessage.toString(), restoredMessage.toString(), "This is original message.");
    }
}
