package raf.dsw.classycraft.app.model.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.observer.ISubscriber;

@Getter
@Setter

public class ProjectExplorer extends ClassyNodeComposite {
    public ProjectExplorer(String name) {

        super(name, null);

    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null && child instanceof Project){
            Project project = (Project) child;
            if (!this.getChildren().contains(project)){
                this.getChildren().add(project);

            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        this.getChildren().remove(child);
        notifySubscribers("remove");
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
}
