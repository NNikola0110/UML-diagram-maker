package raf.dsw.classycraft.app.model.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.net.URL;
@Getter
@Setter
public class Project extends ClassyNodeComposite {

    private static int counter=1;
    private String author;
    private URL projectFile;
    public Project(String name, ClassyNode parent) {
        super(name, parent);
        setName(name+" "+counter);
        counter++;
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null && child instanceof Package){
            Package project = (Package) child;
            if (!this.getChildren().contains(project)){
                this.getChildren().add(project);
                notifySubscribers(child);
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        this.getChildren().remove(child);
        System.out.println("Obavesteni");
        for(ClassyNode c:this.getChildren()){
            ((Package) c).notifySubscribers("removeAll");
        }

    }


    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if (subscriber == null || subs.contains(subscriber)) return;
        subs.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        if (subscriber == null || !(subs.contains((subscriber)))) return;
        subs.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if (notification == null || subs.isEmpty()) return;
        for (ISubscriber s : subs) {
            s.update(this);
        }

    }

    public void setAuthor(String author) {
        this.author = author;
        for(ClassyNode child:this.getChildren()) {
            ((Package) child).setAuthor(author);
        }

    }

    @Override
    public void setName(String name) {           //--------------
        super.setName(name);
        for(ClassyNode child:this.getChildren()){
            ((Package) child).notifySubscribers("name");
        }
    }
}
