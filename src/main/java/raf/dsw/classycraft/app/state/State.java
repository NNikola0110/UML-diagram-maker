package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

public interface State {
    public void misKliknut(Point p, DiagramView dv);
    public void misPritisnut(Point p, DiagramView dv);
    public void misOtpusten(Point p, DiagramView dv);
    public void misPrevucen(Point p, DiagramView dv);

}
