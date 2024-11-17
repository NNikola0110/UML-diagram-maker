package raf.dsw.classycraft.app.model.implementation.interclass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;

@Getter
@Setter
public class Klasa extends Interclass {


    public Klasa(String name, ClassyNode parent) {
        super(name, parent);
    }

    public void setName(String name){
        super.setName(name);

    }
}
