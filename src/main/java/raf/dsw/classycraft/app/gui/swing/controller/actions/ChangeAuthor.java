package raf.dsw.classycraft.app.gui.swing.controller.actions;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.message.MessagesType;
import raf.dsw.classycraft.app.model.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChangeAuthor extends AbstractClassCrafTAction {

    public ChangeAuthor() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/authorization.png"));
        putValue(NAME,"Change author");
        putValue(SHORT_DESCRIPTION,"Change author");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem classyTreeItem= MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(classyTreeItem==null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NODE_NOT_SELECTED);
            return;
        } else if (!(classyTreeItem.getClassyNode() instanceof Project)) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.ERROR);
            return;
        }
        String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Novo ime autora\n", "Promeni ime autora", JOptionPane.QUESTION_MESSAGE);
        if(name==null||name.isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessagesType.NAME_CANNOT_BE_EMPTY);
            return;
        }
        ((Project)classyTreeItem.getClassyNode()).setAuthor(name);
        //MainFrame.getInstance().getClassyTree().editName();
    }
}
