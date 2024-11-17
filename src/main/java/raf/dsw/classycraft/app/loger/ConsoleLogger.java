package raf.dsw.classycraft.app.loger;


import raf.dsw.classycraft.app.message.Message;

public class ConsoleLogger implements Logger{


    @Override
    public void update(Object notification) {
        System.out.println(notification.toString());

    }

    @Override
    public void loger(String error) {

    }
}
