
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private List<User> users;

    public ChatServer(){
        users = new ArrayList<>();
    }

    public List<User> getUsers(){
        return users;
    }

    public void sendMessage(Message message){
        User sender = message.getSender();
        List<User> receivers = new ArrayList<>(message.getReceivers());
        if(!users.contains(sender)){
            System.out.printf("Can't send message as user %s is not registered\n", sender.getUsername());
            return;
        }
        List<User> validReceivers = new ArrayList<>();
        for (User user : receivers){
            if (!users.contains(user)){
                System.out.printf("Cannot send message from %s to %s as user %s is not registered\n",
                        sender.getUsername(), user.getUsername(), user.getUsername());
            }
            else {
                validReceivers.add(user);
            }
        }
        for (User receiver : validReceivers){
            List<User> blockedAccounts = receiver.getBlockedUsers();
            if (blockedAccounts != null && blockedAccounts.contains(sender)){
                System.out.println("Cannot send message from " + sender.getUsername() + " to " + receiver.getUsername() +
                        " since " + sender.getUsername() + " is blocked by " + receiver.getUsername());
            } else{
                sender.sendMessage(message);
                System.out.printf("Successfully sent message from %s to %s\n", sender.getUsername(), receiver.getUsername());
                receiver.receiveMessage(message);
                System.out.printf("%s: %s received message from %s: '%s'\n", message.getTimestamp(),
                        receiver.getUsername(), sender.getUsername(), message.getTextMessage());
            }
        }
    }
    public void registerUser(User user) {
        users.add(user);
        System.out.printf("Successfully registered user %s\n", user.getUsername());
    }
    public void unregisterUser(User user) {
        users.remove(user);
        System.out.printf("Unregistered user %s\n", user.getUsername());
    }
    public void undoLastMessage(User user){
        List<Message> sentMessages = user.getChatHistory().getSentMessages();
        if (sentMessages.size() == 0){
            System.out.printf("Cannot unsend last message as user %s has not sent any messages.\n", user.getUsername());
            return;
        }
        Message message = user.getChatHistory().getLastSentMessages();
        user.undoLastSentMessage();
        List<User> receivers = message.getReceivers();
        for (User receiver : receivers){
            receiver.getChatHistory().removeLastReceivedMessage(message);
        }
    }
}
