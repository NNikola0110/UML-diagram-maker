package raf.dsw.classycraft.app.model.implementation.connection;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;

import java.awt.*;
@Getter
@Setter
public class Agregacija extends Connection {

    private String imePolja;
    private String vidljivost;
    private String kardinalnost;
    private Stroke stroke;
    public Agregacija(String name, ClassyNode parent) {
        super(name, parent);
    }
}
