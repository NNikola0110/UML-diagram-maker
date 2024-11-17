package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.observer.ISubscriber;

public class DiagramFactory extends ClassyNodeFactory{
    public DiagramFactory(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public ClassyNode createChild(ClassyNode node) {
        return node;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {

    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {

    }

    @Override
    public void notifySubscribers(Object notification) {

    }
}
