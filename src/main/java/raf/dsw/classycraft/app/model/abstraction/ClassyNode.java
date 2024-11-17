package raf.dsw.classycraft.app.model.abstraction;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public abstract class ClassyNode implements IPublisher {

    private String name;
    private transient ClassyNode parent;
    protected transient List<ISubscriber> subs;

    public ClassyNode(String name, ClassyNode parent) {
        this.name = name;
        this.parent = parent;
        this.subs=new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
        notifySubscribers(this);

    }

}
