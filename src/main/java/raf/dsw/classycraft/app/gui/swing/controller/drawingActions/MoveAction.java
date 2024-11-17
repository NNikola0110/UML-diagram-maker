package raf.dsw.classycraft.app.gui.swing.controller.drawingActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MoveAction extends AbstractClassCrafTAction {
    public MoveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/move2.png"));
        putValue(NAME,"Move");
        putValue(SHORT_DESCRIPTION,"Move");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startMoveState();
    }
}
