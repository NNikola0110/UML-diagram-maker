package raf.dsw.classycraft.app.gui.swing.controller.actions;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveProjectAction extends AbstractClassCrafTAction {

    public SaveProjectAction(){

        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save Project");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.showSaveDialog(MainFrame.getInstance());
        Project project = (Project) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();

        ApplicationFramework.getInstance().getSerializer().saveProject(project);

    }
}
