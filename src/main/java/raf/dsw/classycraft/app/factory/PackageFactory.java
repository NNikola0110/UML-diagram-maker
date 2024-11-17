package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.Diagram;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;

public class PackageFactory extends ClassyNodeFactory{
    public PackageFactory(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public ClassyNode createChild(ClassyNode node) {
            Diagram diagram = new Diagram("Diagram", node);
            return diagram;

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
