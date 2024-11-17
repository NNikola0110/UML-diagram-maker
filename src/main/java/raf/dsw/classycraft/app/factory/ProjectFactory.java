package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.Package;
import raf.dsw.classycraft.app.model.implementation.Project;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.util.Random;

public class ProjectFactory extends ClassyNodeFactory{
    public ProjectFactory(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public ClassyNode createChild(ClassyNode node) {
        Package projecT=new Package("Paket", node);

        return projecT;
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
