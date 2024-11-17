package raf.dsw.classycraft.app.message;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {

    private String text ;
    private MessagesType messagesType;
    private LocalDateTime time;
    public Message(String text, MessagesType messagesType) {
        this.text = text;
        this.messagesType = messagesType;
        this.time = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return"["+time+"] " +  "["+ messagesType + "] " + text;
    }
}
