package raf.dsw.classycraft.app.gui.swing.controller.drawingActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChangeAction extends AbstractClassCrafTAction {

    public ChangeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(NAME,"Change");
        putValue(SHORT_DESCRIPTION,"Change");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MainFrame.getInstance().getPackageView().startChangeState();
    }
}
