package raf.dsw.classycraft.app.model.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyLeaf;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.awt.*;
@Getter
@Setter
public class DiagramElement extends ClassyLeaf {

    private Color color;
    private Stroke stroke = new BasicStroke(2.5f);

    public DiagramElement(String name, ClassyNode parent) {
        super(name, parent);
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
