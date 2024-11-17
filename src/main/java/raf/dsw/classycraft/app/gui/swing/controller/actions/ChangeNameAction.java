package raf.dsw.classycraft.app.gui.swing.controller.actions;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.message.MessagesType;
import raf.dsw.classycraft.app.model.abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChangeNameAction extends AbstractClassCrafTAction {
    private ClassyTreeView classyTreeView;
    public ChangeNameAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/rename.png"));
        putValue(NAME,"Change name");
        putValue(SHORT_DESCRIPTION,"Change name");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ClassyTreeItem classyTreeItem= MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(classyTreeItem==null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NODE_NOT_SELECTED);
            return;
            }
        String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Novo ime\n", "Promeni ime", JOptionPane.QUESTION_MESSAGE);
        if(name==null||name.isEmpty()){
           // ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.ERROR);
            return;
        }

        /*if(classyTreeItem.getClassyNode() instanceof ProjectExplorer) {
            for (ClassyNode node : ((ProjectExplorer) classyTreeItem.getClassyNode()).getChildren()) {
                if (node.equals(name))
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.ERROR);
            }
        }*/
        if(!(classyTreeItem.getClassyNode() instanceof ProjectExplorer)){
            for (ClassyNode child: ((ClassyNodeComposite)classyTreeItem.getClassyNode().getParent()).getChildren()){
                if (child.getName().equals(name) ) {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NODE_ALREADY_EXISTS);
                    return;
                }
            }
        }else {
            for (ClassyNode child : ((ProjectExplorer) classyTreeItem.getClassyNode()).getChildren()) {
                if (child.getName().equals(name) && !(child.equals(classyTreeItem.getClassyNode()))) {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NODE_ALREADY_EXISTS);
                    return;
                }
            }
        }

        classyTreeItem.setName(name);
        MainFrame.getInstance().getClassyTree().editName();

    }
}
