package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.implementation.connection.Connection;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.painters.connection.ConnectionPainter;
import raf.dsw.classycraft.app.painters.interclass.InterclassPainter;
import raf.dsw.classycraft.app.painters.interclass.KlasaPainter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MoveState implements State{
    double xNovo;
    double yNovo;
    int flag = 0;
    ElementPainter x;
    List<ElementPainter> moveList = new ArrayList<>();
    @Override
    public void misKliknut(Point p, DiagramView dv) {

    }

    @Override
    public void misPritisnut(Point pp, DiagramView dv) {
            Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
            Point2D.Double p = dv.getOriginalCoordinates(point);
            for(ElementPainter ep:dv.getSelectionModel()){
                if(ep instanceof InterclassPainter) {
                    Interclass i = ((InterclassPainter) ep).getInterclass();
                    ep.setXPocetno(i.getLocation().x);
                    ep.setYPocetno(i.getLocation().y);
                    if (ep.elementAt(new Point2D.Double(p.x, p.y))) {
                        xNovo = p.x;
                        yNovo = p.y;
                        flag = 1;
                        x = ep;
                        break;
                    } else {
                        flag = 0;
                    }
                }
            }
    }

    @Override
    public void misOtpusten(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);
        if(flag==1) {
                ElementPainter provera = provera(p, dv);
                if (provera != null) {
                    if (!provera.equals(x)) {
                        for (ElementPainter ep : dv.getSelectionModel()) {
                            if (ep instanceof InterclassPainter) {
                                Interclass i = ((InterclassPainter) ep).getInterclass();
                                i.setLocation(new Point2D.Double(ep.getXPocetno(), ep.getYPocetno()));
                            }
                        }
                        //dv.repaint();
                    } else {
                        System.out.println("Moja klasa");
                    }
                }else{
                    if(proveraPresecanja(dv)){
                        System.out.println("PRESEK");
                        for (ElementPainter ep : dv.getSelectionModel()) {
                            if (ep instanceof InterclassPainter) {
                                Interclass i = ((InterclassPainter) ep).getInterclass();
                                i.setLocation(new Point2D.Double(ep.getXPocetno(), ep.getYPocetno()));
                            }
                        }
                    }
                }
        }
    }
    @Override
    public void misPrevucen(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);
        if(flag==1){
            //ElementPainter provera = provera(p, dv);
            for(ElementPainter ep:dv.getSelectionModel()){
                //if(provera!=null){
                    if(ep instanceof InterclassPainter){
                        Interclass i = ((InterclassPainter)ep).getInterclass();
                        i.setLocation(new Point2D.Double(i.getLocation().x - (xNovo - p.x),i.getLocation().y - (yNovo - p.y)));
                    }
                    dv.repaint();
                //}
            }
            xNovo -= xNovo - p.x;
            yNovo -= yNovo - p.y;
        }
    }

    public boolean proveraPresecanja(DiagramView dv){
        for(ElementPainter selektovan:dv.getSelectionModel()){
            if(selektovan instanceof InterclassPainter) {
                for (ElementPainter element : dv.getPainters()) {
                    if(element instanceof InterclassPainter) {
                        if(!selektovan.equals(element)) {
                            if (selektovan.getShape().intersects(((InterclassPainter) element).getRectangle())) {
                                System.out.println("aaaaaaa");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public ElementPainter provera(Point2D.Double p, DiagramView dv){
        if(!dv.getPainters().isEmpty()){
            for(ElementPainter ep:dv.getPainters()){
                if(ep.elementAt(p)) {
                    if(!ep.equals(x)){
                        return ep;
                    }
                }else if(ep instanceof InterclassPainter){
                    InterclassPainter ip = (InterclassPainter) ep;
                    if(x instanceof InterclassPainter && ip.getRectangle().intersects(((InterclassPainter)x).getRectangle())){
                        System.out.println("Element ATT");
                        return ep;
                    }
                }
            }
            return null;
        }else{
            return null;
        }
    }
}