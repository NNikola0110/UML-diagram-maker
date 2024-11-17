package raf.dsw.classycraft.app.model.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.observer.ISubscriber;

@Setter
@Getter
public class Package extends ClassyNodeComposite  {
    private static int counter=1;
    //private PackageView packageView = new PackageView();
    private String author;

    public Package(String name, ClassyNode parent) {
        super(name, parent);
        setName(name+" "+counter);
        counter++;
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child instanceof Diagram){
            Diagram project = (Diagram) child;
            if (!(this.getChildren().contains(project))){
                this.getChildren().add(project);
                System.out.println("Notify/add");
                notifySubscribers("child");
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {

        this.getChildren().remove(child);
        System.out.println("Notify/remove");
        if(child.getParent() instanceof Project){
            notifySubscribers("remove");
        }else
         notifySubscribers("child");
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
            s.update(notification);
        }
    }


    public void setAuthor(String author) {
        this.author = author;
       notifySubscribers("name");
    }
}
