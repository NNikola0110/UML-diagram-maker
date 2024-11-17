package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

public class ZoomOutState implements State{
    @Override
    public void misKliknut(Point p, DiagramView dv) {
        dv.zoomOut();
    }

    @Override
    public void misPritisnut(Point p, DiagramView dv) {

    }

    @Override
    public void misOtpusten(Point p, DiagramView dv) {

    }

    @Override
    public void misPrevucen(Point p, DiagramView dv) {

    }
}
