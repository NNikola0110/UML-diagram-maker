package raf.dsw.classycraft.app.model.implementation.connection;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;

import java.awt.*;

@Getter
@Setter
public class Kompozicija extends Connection {
    private String imePolja;
    private String vidljivost;
    private String kardinalnost;
    public Kompozicija(String name, ClassyNode parent) {
        super(name, parent);
    }

    public void setImePolja(String imePolja) {
        this.imePolja = imePolja;
        getParent().notifySubscribers("paint");

    }

    public void setKardinalnost(String kardinalnost) {
        this.kardinalnost = kardinalnost;
        getParent().notifySubscribers("paint");

    }
}
