import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ChatHistoryTest {

    @Test
    void testAddSentMessage() {

        ChatHistory chatHistory = new ChatHistory();
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message = new Message(sender, List.of(receiver), "Hello!");
        chatHistory.addSentMessage(message);
        assertEquals(1, chatHistory.getSentMessages().size(), "There should be one sent message.");
        assertEquals(message, chatHistory.getSentMessages().get(0), "There should be  one sent message.");
    }

    @Test
    void testAddReceivedMessage() {
        ChatHistory chatHistory = new ChatHistory();
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message = new Message(sender, List.of(receiver), "Hello!");
        chatHistory.addReceivedMessage(message);
        assertEquals(1, chatHistory.combineMessages().size(), "Combined messages.");
        assertTrue(chatHistory.combineMessages().contains(message), "Received message in the combined message.");
    }

    @Test
    void testGetLastSentMessage() {
        ChatHistory chatHistory = new ChatHistory();
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message1 = new Message(sender, List.of(receiver), "Message 1");
        Message message2 = new Message(sender, List.of(receiver), "Message 2");
        chatHistory.addSentMessage(message1);
        chatHistory.addSentMessage(message2);
        Message lastSentMessage = chatHistory.getLastSentMessages();
        assertEquals(message2, lastSentMessage, "The last sent message should match the most recently added one.");
    }

    @Test
    void testRemoveLastSentMessage() {
        ChatHistory chatHistory = new ChatHistory();
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message = new Message(sender, List.of(receiver), "Message to remove");
        chatHistory.addSentMessage(message);
        chatHistory.removeLastSentMessage(message);
        assertFalse(chatHistory.getSentMessages().contains(message), "Sent messages should not contain the removed message.");
    }

    @Test
    void testCombineMessages() {
        ChatHistory chatHistory = new ChatHistory();
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message sentMessage = new Message(sender, List.of(receiver), "Sent Message");
        Message receivedMessage = new Message(receiver, List.of(sender), "Received Message");
        chatHistory.addSentMessage(sentMessage);
        chatHistory.addReceivedMessage(receivedMessage);
        List<Message> combinedMessages = chatHistory.combineMessages();
        assertEquals(2, combinedMessages.size(), "Both sent and received messages from the combined messages.");
        assertTrue(combinedMessages.contains(sentMessage), "the sent message from the combined messages.");
        assertTrue(combinedMessages.contains(receivedMessage), "the received message from the combined messages.");
    }

    @Test
    void testIterator() {
        ChatHistory chatHistory = new ChatHistory();
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message1 = new Message(sender, List.of(receiver), "Message 1");
        Message message2 = new Message(receiver, List.of(sender), "Message 2");
        chatHistory.addSentMessage(message1);
        chatHistory.addReceivedMessage(message2);
        Iterator<Message> iterator = chatHistory.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        assertEquals(2, count, "Iterator should iterate over all combined messages.");
    }
}
