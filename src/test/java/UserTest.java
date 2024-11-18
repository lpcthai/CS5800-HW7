import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class UserTest {
    @Test
    void testSendMessage() {
        ChatServer chatServer = new ChatServer();
        User sender = new User("Estefania", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message = new Message(sender, List.of(receiver), "Hello!");
        sender.sendMessage(message);
        assertEquals(1, sender.getChatHistory().getSentMessages().size(),
                "Sender's sent messages should contain the message.");
        assertEquals(message, sender.getChatHistory().getSentMessages().get(0),
                "The sent message should match the original message.");
    }

    @Test
    void testReceiveMessage() {
        ChatServer chatServer = new ChatServer();
        User sender = new User("Chloe", chatServer);
        User receiver = new User("Estefania", chatServer);
        Message message = new Message(sender, List.of(receiver), "Hello, Estefania!");
        receiver.receiveMessage(message);
        assertEquals(1, receiver.getChatHistory().combineMessages().size(),
                "Receiver's combined messages in the received message.");
        assertTrue(receiver.getChatHistory().combineMessages().contains(message),
                "The received message should match the original message.");
    }

    @Test
    void testUndoLastSentMessage() {
        ChatServer chatServer = new ChatServer();
        User sender = new User("Estefania", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message = new Message(sender, List.of(receiver), "Hello, Chloe!");
        sender.sendMessage(message);
        sender.undoLastSentMessage();
        assertEquals(0, sender.getChatHistory().getSentMessages().size(),
                "Sender's sent messages should be empty after undoing.");
    }

    @Test
    void testBlockUser() {
        ChatServer chatServer = new ChatServer();
        User blocker = new User("Chloe", chatServer);
        User blocked = new User("Larry", chatServer);
        blocker.blockerUsers(blocked);
        assertTrue(blocker.getBlockedUsers().contains(blocked),
                " User blocked users list.");
    }

    @Test
    void testIterator() {
        ChatServer chatServer = new ChatServer();
        User user = new User("Larry", chatServer);
        Message message1 = new Message(user, List.of(), "Message 1");
        Message message2 = new Message(user, List.of(), "Message 2");
        user.sendMessage(message1);
        user.sendMessage(message2);
        int count = 0;
        for (Message message : user) {
            count++;
        }

        assertEquals(2, count,
                "iterate through all messages in the user's chat history.");
    }
}
