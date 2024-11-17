package raf.dsw.classycraft.app.gui.swing.tree;


import raf.dsw.classycraft.app.factory.ClassyNodeFactory;
import raf.dsw.classycraft.app.factory.FactoryUtiles;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.implementation.Diagram;
import raf.dsw.classycraft.app.model.implementation.DiagramElement;
import raf.dsw.classycraft.app.model.implementation.ProjectExplorer;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.List;

public class ClassyTreeImplementation implements ClassyTree{

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }


    @Override
    public void addChild(ClassyTreeItem parent) {
        if (!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;
        ClassyNode child = createChild(parent.getClassyNode());
        parent.add(new ClassyTreeItem(child));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public void addDiagramChild(Diagram d, ClassyNode child) {

        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        ClassyTreeItem parent = (ClassyTreeItem) findNodeByUserObject(selected,d);

        parent.add(new ClassyTreeItem(child));
        ((ClassyNodeComposite)parent.getClassyNode()).addChild(child);
        SwingUtilities.updateComponentTreeUI(treeView);
    }
    public static DefaultMutableTreeNode findNodeByUserObject(DefaultMutableTreeNode root, ClassyNode diagram) {
        if (root == null || diagram == null) {
            return null;
        }

        if (diagram.equals(((ClassyTreeItem)root).getClassyNode())) {
            return root;
        }

        Enumeration<TreeNode> children = root.children();
        while (children.hasMoreElements()) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
            DefaultMutableTreeNode foundNode = findNodeByUserObject(child, diagram);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }



    @Override
    public ClassyTreeItem getSelectedNode() {

        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void editName() {

        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(ClassyTreeItem selected) {
        selected.removeAllChildren();
        selected.removeFromParent();
    if(selected.getClassyNode() instanceof ClassyNodeComposite) {
        ((ClassyNodeComposite) selected.getClassyNode().getParent()).removeChild(selected.getClassyNode());
    }else{
        ((ClassyNodeComposite) selected.getClassyNode().getParent()).removeChild(selected.getClassyNode());
    }
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    public void removeDiagramChild(DiagramElement diagramElement){
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        ClassyTreeItem parent = (ClassyTreeItem) findNodeByUserObject(selected,diagramElement.getParent());
        ClassyTreeItem child = (ClassyTreeItem) findNodeByUserObject(parent,diagramElement);
        System.out.println("UKLONI");
        //child.removeFromParent();
        if(child!=null) {
            parent.remove(child);
            ((ClassyNodeComposite) parent.getClassyNode()).removeChild(diagramElement);
            SwingUtilities.updateComponentTreeUI(treeView);
        }
    }

    @Override
    public ClassyTreeView getTreeView() {
        return treeView;
    }

    private ClassyNode createChild(ClassyNode parent) {
        ClassyNodeFactory classyNodeFactory= FactoryUtiles.getFactory(parent);
        if (classyNodeFactory != null) {
            return classyNodeFactory.returnChild(parent);
        }
        return null;
    }


}
