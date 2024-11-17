package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    private DiagramView diagramView;

    public Mouse(DiagramView diagramView) {
        this.diagramView = diagramView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mis kliknut");
        MainFrame.getInstance().getPackageView().misKliknut(e.getPoint(),diagramView);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getPackageView().misPritisnut(e.getPoint(),diagramView);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getPackageView().misOtpusten(e.getPoint(),diagramView);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getPackageView().misPrevucen(e.getPoint(),diagramView);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
