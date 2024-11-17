package raf.dsw.classycraft.app.listener;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.implementation.Package;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListenerForTree extends MouseAdapter   {


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
            if(selected.getClassyNode() instanceof Package) {
                MainFrame.getInstance().getPackageView().openView((ClassyNodeComposite) selected.getClassyNode());
            }




            /*if(selected.getClassyNode() instanceof Package) {
                if (((Package) selected.getClassyNode()).getPackageView() == null) {
                    //MainFrame.getInstance().getPackageView().setAuthor(new JLabel((Project)selected.getClassyNode()).set);
                    MainFrame.getInstance().getPackageView().setProjectName(new JLabel(selected.getParent().toString()));
                   // PackageView packageView = new PackageView();
                    //packageView.reloadTabs(selected.getClassyNode());
                    MainFrame.getInstance().getPackageView().setDiagramViews();
                } else {
                    ((Package) selected.getClassyNode()).getPackageView().setDiagramViews();
                    //MainFrame.getInstance().getPackageView().setVisible(true);
                }
            }*/

        }
    }


}
