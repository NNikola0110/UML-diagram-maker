package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.model.abstraction.ClassyNode;

public abstract class ClassyNodeFactory extends ClassyNode{

    public ClassyNodeFactory(String name, ClassyNode parent) {
        super(name, parent);
    }

    public ClassyNode returnChild(ClassyNode parent){
        ClassyNode classyNode=createChild(parent);
        classyNode.setParent(parent);
        return classyNode;
    }

    public abstract ClassyNode createChild(ClassyNode node);

}
