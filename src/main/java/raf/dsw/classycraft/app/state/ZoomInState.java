package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.painters.interclass.InterclassPainter;

import java.awt.*;

public class ZoomInState implements State{
    @Override
    public void misKliknut(Point p, DiagramView dv) {
        dv.zoomIn();
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
