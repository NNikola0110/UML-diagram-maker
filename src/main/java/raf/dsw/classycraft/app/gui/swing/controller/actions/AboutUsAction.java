package raf.dsw.classycraft.app.gui.swing.controller.actions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.view.AboutUsWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUsAction extends AbstractClassCrafTAction {

    public AboutUsAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON,loadIcon("/images/AboutUs.png"));
        putValue(NAME, "About us");
        putValue(SHORT_DESCRIPTION, "About us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AboutUsWindow aboutUsWindow=new AboutUsWindow();
        aboutUsWindow.setVisible(true);
    }
}
