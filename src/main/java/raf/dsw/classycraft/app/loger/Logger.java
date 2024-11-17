package raf.dsw.classycraft.app.loger;

import raf.dsw.classycraft.app.observer.ISubscriber;

public interface Logger extends ISubscriber {

    void loger(String error);

}
