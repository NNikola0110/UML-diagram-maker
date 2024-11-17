package raf.dsw.classycraft.app.gui.swing.tree;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.implementation.Diagram;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.ProjectExplorer;

public interface ClassyTree {

    ClassyTreeView generateTree(ProjectExplorer projectExplorer);

    void addChild(ClassyTreeItem parent);
    void addDiagramChild(Diagram d, ClassyNode child);
    ClassyTreeItem getSelectedNode();
    void editName();

    void removeChild(ClassyTreeItem selected);
    public void removeDiagramChild(DiagramElement diagramElement);
    ClassyTreeView getTreeView();
}
