package raf.dsw.classycraft.app.message;

import raf.dsw.classycraft.app.observer.IPublisher;

public interface MessageGenerator extends IPublisher {

    void generateMessage(MessagesType messagesType);
}
