package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.Project;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;

public class ProjectExplorerFactory extends ClassyNodeFactory{
    public ProjectExplorerFactory(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public ClassyNode createChild(ClassyNode node) {
       Project project=new Project("Projekat",node);
        String s= JOptionPane.showInputDialog("Ime autora:  ");
        project.setAuthor(s);
        return project;
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
