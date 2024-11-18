import java.util.Iterator;
import java.util.List;
public class Driver {
    private static final ChatServer chatServer = new ChatServer();
    public static void main(String[] args) {
        User userChloe = new User("Chloe", chatServer);
        User userLarry = new User("Larry", chatServer);
        User userEstef = new User("Estefania", chatServer);
        chatServer.sendMessage(new Message(userChloe, List.of(userLarry), " Hello, Larry! I'm Chloe!"));
        chatServer.sendMessage(new Message(userChloe, List.of(userEstef), "Hello, Estefania! I'm Chloe!"));
        chatServer.sendMessage(new Message(userEstef, List.of(userChloe), "Hello, Chloe. I'm Estefania!"));
        userLarry.blockerUsers(userEstef);
        chatServer.sendMessage(new Message(userChloe, List.of(userLarry, userEstef), "I have a plan for Thanksgiving party. Do you wanna join?"));
        chatServer.sendMessage(new Message(userEstef, List.of(userChloe), "Yes, I am down!"));
        System.out.println("\nEstefania unsent last message:");
        chatServer.undoLastMessage(userEstef);

        Message lastMessage = userEstef.getChatHistory().getLastSentMessages();
        if (lastMessage == null) {
            System.out.println("No messages to display for Estefania.");
        } else {
            System.out.printf("Now, Estefania's last message is \n", lastMessage);
        }
        System.out.println("\nLarry unsent last message:");
        chatServer.undoLastMessage(userLarry);

        System.out.println("\nIterating all messages in Estefania's chat history:");
        Iterator<Message> allMessagesIterator = userEstef.iterator();
        while (allMessagesIterator.hasNext()) {
            System.out.println(allMessagesIterator.next());
        }

        System.out.println("\nIterating all messages in Chloe's chat history:");
        allMessagesIterator = userChloe.iterator();
        while (allMessagesIterator.hasNext()) {
            System.out.println(allMessagesIterator.next());
        }
        chatServer.unregisterUser(userChloe);
        System.out.println("Chloe has been unregistered.");
        chatServer.sendMessage(new Message(userLarry, List.of(userChloe), "Let me know soon!"));
    }
}



