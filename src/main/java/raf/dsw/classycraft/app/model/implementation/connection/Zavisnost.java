package raf.dsw.classycraft.app.model.implementation.connection;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;

@Getter
@Setter
public class Zavisnost extends Connection {

    private String tip;

    public Zavisnost(String name, ClassyNode parent) {
        super(name, parent);
    }

    public void setTip(String tip) {
        this.tip = tip;
        getParent().notifySubscribers("paint");
    }

}
