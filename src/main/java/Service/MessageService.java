package Service;

import java.util.List;
import DAO.MessageDAO;
import Model.Message;
import Model.Account;
import DAO.AccountDAO;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    //Constructor for an AccountService when a AccountDAO is provided.
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    //if message is not blank, message length is < 255, and posted_by is an existing user
    public Message createMessage(Message message){
        if(message.getMessage_text() != "" && message.getMessage_text().length() < 255){
            if(messageDAO.getAccountByUsername(message.getPosted_by())==message){ 
                return messageDAO.createMessage(message); 
            }
        }
        return null;
    }
    //retrieve all messages
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }
    //get message by message_id
    public Message getMessageById(Message message){
        if(message.equals(messageDAO.getMessageById(message.getMessage_id())))
         { return messageDAO.getMessageById(message.getMessage_id()); }
         else
         { return null; }
    }

    //get messages by particular user
    public List<Message> getMessagesByUser(Account account){
        if(accountDAO.getAccountByUsername(account.getUsername()) == null){
            return messageDAO.getAllMessagesByUser(account);
        }
        else
            { return null; }
    }

    //delete message by message_id
    public Message deleteMessageById(Message message){
        if(message.equals(messageDAO.getMessageById(message.getMessage_id())))
        { return messageDAO.deleteMessageById(message); }
        else 
        { return null; }
    }

    /* update message by message_id if the message_id already exists 
    and the new message_text is not blank and is not over 255 characters*/
    public Message updateMessageById(Message message){
        if(message.equals(messageDAO.getMessageById(message.getMessage_id()))){
            if(message.getMessage_text() != "" && message.getMessage_text().length() < 255){
                return messageDAO.updateMessageById(message);
            }
        }
        return null;
    }
}
