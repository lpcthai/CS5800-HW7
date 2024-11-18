import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchMessageByUserTest {

    @Test
    void testHasNextWhenUser() {
        ChatServer chatServer = new ChatServer();
        User userToSearch = new User("Larry", chatServer);
        User otherUser = new User("Estefania", chatServer);
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(userToSearch, List.of(otherUser), "Message 1"));
        messages.add(new Message(otherUser, List.of(userToSearch), "Message 2"));
        messages.add(new Message(otherUser, List.of(otherUser), "Message 3"));
        Iterator<Message> messageIterator = messages.iterator();
        SearchMessageByUser searchIterator = new SearchMessageByUser(messageIterator, userToSearch);
        assertTrue(searchIterator.hasNext(), "message's iterator find from Message.");
    }

    @Test
    void testHasNext() {
        ChatServer chatServer = new ChatServer();
        User userToSearch = new User("Larry", chatServer);
        User otherUser = new User("Estefania", chatServer);
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(otherUser, List.of(otherUser), "Message 1"));
        messages.add(new Message(otherUser, List.of(otherUser), "Message 2"));
        Iterator<Message> messageIterator = messages.iterator();
        SearchMessageByUser searchIterator = new SearchMessageByUser(messageIterator, userToSearch);
        assertFalse(searchIterator.hasNext(), "Iterator should not find from message.");
    }

    @Test
    void testNextMessage() {
        ChatServer chatServer = new ChatServer();
        User userToSearch = new User("Larry", chatServer);
        User otherUser = new User("Estefania", chatServer);
        Message expectedMessage = new Message(userToSearch, List.of(otherUser), "Message 1");
        List<Message> messages = new ArrayList<>();
        messages.add(expectedMessage);
        messages.add(new Message(otherUser, List.of(otherUser), "Message 2"));

        Iterator<Message> messageIterator = messages.iterator();
        SearchMessageByUser searchIterator = new SearchMessageByUser(messageIterator, userToSearch);
        Message result = searchIterator.next();
        assertEquals(expectedMessage, result, "Iterator should return the correct message from  Message.");
    }

    @Test
    void testNextReturnsNullWhenNoMessagesMatch() {
        ChatServer chatServer = new ChatServer();
        User userToSearch = new User("Larry", chatServer);
        User otherUser = new User("Estefania", chatServer);
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(otherUser, List.of(otherUser), "Message 1"));
        messages.add(new Message(otherUser, List.of(otherUser), "Message 2"));
        Iterator<Message> messageIterator = messages.iterator();
        SearchMessageByUser searchIterator = new SearchMessageByUser(messageIterator, userToSearch);
        Message result = searchIterator.next();
        assertNull(result, "Iterator should return null when no messages match.");
    }


}
