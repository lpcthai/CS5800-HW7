import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatServerTest {

    @Test
    void testRegisterUser() {
        ChatServer chatServer = new ChatServer();
        User receiver = new User("Chloe", chatServer);
        chatServer.registerUser(receiver);
        assertTrue(chatServer.getUsers().contains(receiver), "Successfully registered user.");
    }

    @Test
    void testUnregisterUser() {
        ChatServer chatServer = new ChatServer();
        User receiver = new User("Chloe", chatServer);
        chatServer.unregisterUser(receiver);
        assertFalse(chatServer.getUsers().contains(receiver), " Successfully unregistered.");
    }


    @Test
    void testSendMessage() {
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message = new Message(sender, List.of(receiver), "Hello, Chloe!");
        chatServer.sendMessage(message);
        assertEquals(1, sender.getChatHistory().getSentMessages().size(), "Sender's sent messages.");
        assertEquals(1, receiver.getChatHistory().combineMessages().size(), "Receiver should receive the message.");
    }

    @Test
    void testSendMessageToUnregisteredUser() {
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", new ChatServer());
        Message message = new Message(sender, List.of(receiver), "Hello!");
        chatServer.sendMessage(message);
        assertEquals(0, sender.getChatHistory().getSentMessages().size(), "Receiver's sent messages could not be sent.");
        assertEquals(0, receiver.getChatHistory().combineMessages().size(), "Receiver should not have any messages.");
    }



    @Test
    void testUndoLastMessage() {
        ChatServer chatServer = new ChatServer();
        User sender = new User("Larry", chatServer);
        User receiver = new User("Chloe", chatServer);
        Message message = new Message(sender, List.of(receiver), "Hello!");
        chatServer.sendMessage(message);
        chatServer.undoLastMessage(sender);
        assertEquals(0, sender.getChatHistory().getSentMessages().size(), " Last sent message should be undone.");
        assertEquals(0, receiver.getChatHistory().combineMessages().size(), "Received messages should be undone.");
    }
}
