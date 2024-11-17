package raf.dsw.classycraft.app.gui.swing.controller.actions;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.message.MessagesType;
import raf.dsw.classycraft.app.model.implementation.Project;
import raf.dsw.classycraft.app.model.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RemoveAction extends AbstractClassCrafTAction {
    public RemoveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/remove.png"));
        putValue(NAME, "Remove");
        putValue(SHORT_DESCRIPTION, "Remove");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected==null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.ERROR);
            return;

        }
        else if(selected.getClassyNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NODE_CANNOT_BE_DELETED);
            return;
        }

        MainFrame.getInstance().getClassyTree().removeChild(selected);
    }
}
