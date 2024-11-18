public class MessageMemento {
    private Message message;

    public MessageMemento(Message message){
        this.message = message;
    }

    public Message getPreviousMessage(){
        return message;
    }
}