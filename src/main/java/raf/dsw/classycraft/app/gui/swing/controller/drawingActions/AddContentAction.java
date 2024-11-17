package raf.dsw.classycraft.app.gui.swing.controller.drawingActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddContentAction extends AbstractClassCrafTAction {

    public AddContentAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/playlist.png"));
        putValue(NAME,"Add content");
        putValue(SHORT_DESCRIPTION,"Add content");
    }



    @Override
    public void actionPerformed(ActionEvent e) {


        Object[] obj={"atribut","metoda","enum"};
        int s = JOptionPane.showOptionDialog(null, "Sta hocete da dodate: ", "Izaberi",
                0, 3, null, obj, obj[0]);

        if(s==2) //enum element
        {
            MainFrame.getInstance().getPackageView().startContentState("enum");

        }
        else if(s==1) //metoda
        {
            MainFrame.getInstance().getPackageView().startContentState("metoda");
        }

        else if(s==0) //atribut
        {
            MainFrame.getInstance().getPackageView().startContentState("atribut");
        }
    }
}
