package raf.dsw.classycraft.app.model.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyLeaf;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.io.IOException;

@Getter
@Setter
public class Diagram extends ClassyNodeComposite {
    private static int counter=1;
    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
        setName(name+" "+counter);
        counter++;
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child instanceof DiagramElement){
            DiagramElement diagramElement = (DiagramElement) child;
            if (!(this.getChildren().contains(diagramElement))){
                this.getChildren().add(diagramElement);
                System.out.println("Notify/add za dijagramelem");
                notifySubscribers("paint");
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        this.getChildren().remove(child);
        System.out.println("Remove za elem notify");
        notifySubscribers("paint");

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
      try {
          if (notification == null || subs.isEmpty()) {
              return;
          }
          for(ISubscriber s:subs){
              //System.out.println(s);
              s.update(notification);
          }
      }catch (Exception e){
          System.out.println("greska");
      }


    }

    @Override
    public boolean equals(Object obj) {
        return super.getName().equals(((ClassyNode)obj).getName());
    }
}
