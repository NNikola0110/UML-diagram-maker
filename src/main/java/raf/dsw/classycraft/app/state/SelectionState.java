package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.painters.connection.ConnectionPainter;
import raf.dsw.classycraft.app.painters.interclass.InterclassPainter;
import raf.dsw.classycraft.app.painters.interclass.LassoPainter;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class SelectionState implements State{
    private LassoPainter lp;
    @Override
    public void misKliknut(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);

        ElementPainter provera = provera(p,dv);
        if(provera != null){
            if(!dv.getSelectionModel().contains(provera)){
            System.out.println("Usao");
            dv.getSelectionModel().add(provera);
            }else{
                System.out.println("Vec selektovan");
                dv.getSelectionModel().remove(provera);
            }
        }else{
            System.out.println("Sve skloni");
            dv.getSelectionModel().clear();
        }
        dv.repaint();
    }

    @Override
    public void misPritisnut(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);
        lp = new LassoPainter(p);
        lp.setEnd(p);
        lp.setShape(new Rectangle());
        dv.getPainters().add(lp);
        dv.repaint();
    }

    @Override
    public void misOtpusten(Point p, DiagramView dv) {
        dv.getPainters().remove(lp);
        dv.repaint();
    }

    @Override
    public void misPrevucen(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);
        for(ElementPainter ep :dv.getPainters()){
            if(ep instanceof InterclassPainter){
                if(lp.getShape().intersects(((InterclassPainter) ep).getRectangle())){
                    if(!dv.getSelectionModel().contains(ep)){
                        System.out.println("Usao");
                        dv.getSelectionModel().add(ep);
                    }else{
                        System.out.println("Vec selektovan");
                    }
                }
            }else if(ep instanceof ConnectionPainter){
                if(((Rectangle2D.Double)lp.getShape()).intersectsLine((Line2D)ep.getShape())) {
                    if(!dv.getSelectionModel().contains(ep)){
                        System.out.println("Usao");
                        dv.getSelectionModel().add(ep);
                    }
                }
            }
            lp.setEnd(p);
            dv.repaint();

        }

    }

    public ElementPainter provera(Point2D.Double p, DiagramView dv){
        if(!dv.getPainters().isEmpty()){
            for(ElementPainter ep:dv.getPainters()){
                if(ep.elementAt(p)) {
                    System.out.println("Moze da se selektuje.");
                    return ep;
                }
            }
            return null;
        }else{
            return null;
        }
    }
}
