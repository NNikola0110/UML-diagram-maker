package raf.dsw.classycraft.app.gui.swing.controller.actions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassCrafTAction;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageAction extends AbstractClassCrafTAction {

    public ImageAction() {
        putValue(SMALL_ICON, loadIcon("/images/picture.png"));
        putValue(NAME, "Image");
        putValue(SHORT_DESCRIPTION, "Image");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedDiagraView = MainFrame.getInstance().getPackageView().getJTabbedPane().getSelectedIndex();
         DiagramView dv = MainFrame.getInstance().getPackageView().getDiagramViews().get(selectedDiagraView);

        JFileChooser jfc = new JFileChooser();
        if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File filePicture = jfc.getSelectedFile();

            BufferedImage image = dv.createImage();
            System.out.println(image);

            try {
                ImageIO.write(image, "png", new File(filePicture.getAbsolutePath()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
