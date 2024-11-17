package raf.dsw.classycraft.app.gui.swing.controller.drawingActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddInterclassAction extends AbstractClassCrafTAction {

    public AddInterclassAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/add.png"));
        putValue(NAME,"Add interclass");
        putValue(SHORT_DESCRIPTION,"Add interclass");
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        Object[] obj={"klasa","interfejs","enum"};
        int s = JOptionPane.showOptionDialog(null, "Izaberi", "Izaberi!",
                0, 3, null, obj, obj[0]);

        if(s==2){
            MainFrame.getInstance().getPackageView().startInterclassState("enum");
        }
        else if(s==1) {
            MainFrame.getInstance().getPackageView().startInterclassState("interfejs");
        }
        else if(s==0){
            MainFrame.getInstance().getPackageView().startInterclassState("klasa");


        }

    }
}
