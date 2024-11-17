package raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public abstract class AbstractClassCrafTAction extends AbstractAction {

    public Icon loadIcon(String fileName) {

        URL imageURL = getClass().getResource(fileName);
        Icon icon=null;

        if(imageURL!=null){

            Image image =new ImageIcon(imageURL).getImage();
            Image newImage = image.getScaledInstance(32,32,Image.SCALE_DEFAULT);
            icon=new ImageIcon(newImage);

        }else{
            System.out.println("Nije pronadjena slika na adresi "+fileName);
        }

    return icon;
    }
}
