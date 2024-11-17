package raf.dsw.classycraft.app.gui.swing.tree.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;

import javax.swing.tree.DefaultMutableTreeNode;

@Getter
@Setter
public class ClassyTreeItem extends DefaultMutableTreeNode {

    private ClassyNode classyNode;

    public ClassyTreeItem(ClassyNode classyNode) {
        this.classyNode = classyNode;
    }

    @Override
    public String toString() {
        return classyNode.getName();
    }

    public void setName(String name) {
        this.classyNode.setName(name);
    }
}
