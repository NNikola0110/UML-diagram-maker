package raf.dsw.classycraft.app.gui.swing.view;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class PackageViewToolbar extends JToolBar {
    public PackageViewToolbar() {
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getAddInterclassAction());
        add(MainFrame.getInstance().getActionManager().getAddConnectionAction());
        add(MainFrame.getInstance().getActionManager().getAddContentAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(MainFrame.getInstance().getActionManager().getSelectAction());
        add(MainFrame.getInstance().getActionManager().getMoveAction());
        add(MainFrame.getInstance().getActionManager().getDuplicateAction());
        add(MainFrame.getInstance().getActionManager().getChangeAction());
        add(MainFrame.getInstance().getActionManager().getZoomInAction());
        add(MainFrame.getInstance().getActionManager().getZoomOutAction());
        add(MainFrame.getInstance().getActionManager().getZoomToFitAction());

    }
}
