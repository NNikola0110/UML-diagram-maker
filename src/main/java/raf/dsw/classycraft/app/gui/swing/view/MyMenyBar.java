package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar(){
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        fileMenu.setMnemonic(KeyEvent.VK_F);   //skracenica na tastaturi za otvaranje MenuBar-a
        helpMenu.setMnemonic(KeyEvent.VK_H);   //skracenica na tastaturi za otvaranje MenuBar-a
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        add(fileMenu); // dodavanje menija
        fileMenu.add(MainFrame.getInstance().getActionManager().getProjectAction());
        add(fileMenu);
        fileMenu.add(MainFrame.getInstance().getActionManager().getRemoveAction());
        add(fileMenu);
        fileMenu.add(MainFrame.getInstance().getActionManager().getChangeNameAction());
        add(fileMenu);
        fileMenu.add(MainFrame.getInstance().getActionManager().getChangeAuthor());
        add(fileMenu);
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        add(fileMenu);



        helpMenu.add(MainFrame.getInstance().getActionManager().getAboutUsAction());  //dodavanje about us menija
        add(helpMenu);  //dodavanje dugmeta na pozadinu
    }
}
