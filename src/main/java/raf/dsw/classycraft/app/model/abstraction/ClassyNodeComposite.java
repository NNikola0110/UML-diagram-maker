package raf.dsw.classycraft.app.model.abstraction;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public abstract class ClassyNodeComposite extends ClassyNode {

    private List<ClassyNode> children;

    public ClassyNodeComposite(String name, ClassyNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();

    }

    public abstract void addChild(ClassyNode child);
    public abstract void removeChild(ClassyNode child);
}
