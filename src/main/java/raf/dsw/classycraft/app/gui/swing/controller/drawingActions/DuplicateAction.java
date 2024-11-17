package raf.dsw.classycraft.app.gui.swing.controller.drawingActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DuplicateAction extends AbstractClassCrafTAction {

    public DuplicateAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/duplicate.png"));
        putValue(NAME,"Duplicate");
        putValue(SHORT_DESCRIPTION,"Duplicate");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startDuplicateState();
    }
}
