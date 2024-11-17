package raf.dsw.classycraft.app.model.abstraction;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;

@Getter
@Setter

public abstract class ClassyLeaf extends ClassyNode {


    public ClassyLeaf(String name, ClassyNode parent) {
        super(name, parent);
    }
}
