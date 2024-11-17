package raf.dsw.classycraft.app.message;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static raf.dsw.classycraft.app.message.MessagesType.*;

public class MessageGeneratorImplementation implements MessageGenerator  {

    private Message message;
   private List<ISubscriber> subscribers = new ArrayList<>();

    @Override
    public void generateMessage(MessagesType messagesType) {

        if(ERROR.equals(messagesType)){
            createMessage("Greška",messagesType);
        }
        else if(NODE_CANNOT_BE_DELETED.equals(messagesType)){
            createMessage("Komponenta ne može biti obrisana",messagesType);
        }
        else if(NAME_CANNOT_BE_EMPTY.equals(messagesType)){
            createMessage("Ime ne može da bude prazno",messagesType);
        }
        else if(CANNOT_ADD_CHILD.equals(messagesType)){
            createMessage("Ne može da se doda dete",messagesType);
        }else if(NODE_NOT_SELECTED.equals(messagesType)) {
            createMessage("Nije selektovan cvor", messagesType);
        }else if(NODE_ALREADY_EXISTS.equals(messagesType)){
            createMessage("Takav cvor vec postoji",messagesType);
        }
    }


    public void createMessage(String text, MessagesType messagesType){
        message=new Message(text,messagesType);
        notifySubscribers(message);
    }


    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if(subscriber == null)
            return;
        if(this.subscribers.contains(subscriber))
            return;
        this.subscribers.add(subscriber);
        System.out.println("Dodat je");
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        if(subscriber == null || this.subscribers == null || !this.subscribers.contains(subscriber))
            return;
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null || this.subscribers.isEmpty())
            return;

        for(ISubscriber listener : subscribers){
            listener.update(this.message);
        }
    }
}
