package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.painters.interclass.InterclassPainter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class ZoomToFitState implements State{

    private List<Rectangle2D> shapes = new ArrayList<>();
    @Override
    public void misKliknut(Point pp, DiagramView dv) {
        zoomToFit(dv);
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

    private void zoomToFit(DiagramView dv) {
        if (dv.getPainters().isEmpty()) {
            return;
        }

        List<Rectangle2D> shapes = new ArrayList<>();

        for (ElementPainter ep : dv.getPainters()) {
            if (ep instanceof InterclassPainter) {
                shapes.add(((InterclassPainter) ep).getRectangle());
            }
        }

        if (shapes.isEmpty()) {
            return;
        }

        Rectangle2D boundingBox = izracunajGranice(shapes);

        System.out.println("Bounding Box: " + boundingBox);

        double scaleX = dv.getWidth() / boundingBox.getWidth();
        double scaleY = dv.getHeight() / boundingBox.getHeight();
        double zoomFactor = Math.min(scaleX, scaleY);

        System.out.println("Zoom Factor: " + zoomFactor);

        dv.setPreferredSize(new Dimension((int) (boundingBox.getWidth() * zoomFactor), (int) (boundingBox.getHeight() * zoomFactor)));

        dv.repaint();
    }

    private Rectangle2D izracunajGranice(List<Rectangle2D> shapes) {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for (Rectangle2D shape : shapes) {
            minX = Math.min(minX, shape.getMinX());
            minY = Math.min(minY, shape.getMinY());
            maxX = Math.max(maxX, shape.getMaxX());
            maxY = Math.max(maxY, shape.getMaxY());
        }

        return new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
    }
}
