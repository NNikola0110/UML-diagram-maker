package raf.dsw.classycraft.app.gui.swing.tree.view;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.model.implementation.*;
import raf.dsw.classycraft.app.model.implementation.Package;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        URL imageURL = null;

        if (((ClassyTreeItem) value).getClassyNode() instanceof ProjectExplorer) {
            imageURL = getClass().getResource("/images/project-management.png");
        } else if (((ClassyTreeItem) value).getClassyNode() instanceof Project) {
            imageURL = getClass().getResource("/images/rocket.png");
        }else if (((ClassyTreeItem) value).getClassyNode() instanceof Package) {
            imageURL = getClass().getResource("/images/folder.png");

        }else if (((ClassyTreeItem) value).getClassyNode() instanceof Diagram) {
            imageURL = getClass().getResource("/images/diagram.png");

        }else if (((ClassyTreeItem) value).getClassyNode() instanceof Interclass) {
            imageURL = getClass().getResource("/images/shape-design.png");

        }else if (((ClassyTreeItem) value).getClassyNode() instanceof Connection) {
            imageURL = getClass().getResource("/images/link.png");

        }

        ImageIcon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        Image image = icon.getImage();
        Image modImage = image.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        icon = new ImageIcon(modImage);
        setIcon(icon);
        return this;
    }

}
