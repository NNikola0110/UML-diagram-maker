package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.implementation.interclass.Enumm;
import raf.dsw.classycraft.app.model.implementation.interclass.Interclass;
import raf.dsw.classycraft.app.model.implementation.interclass.Interfejs;
import raf.dsw.classycraft.app.model.implementation.interclass.Klasa;
import raf.dsw.classycraft.app.painters.ElementPainter;
import raf.dsw.classycraft.app.painters.interclass.EnumPainter;
import raf.dsw.classycraft.app.painters.interclass.InterclassPainter;
import raf.dsw.classycraft.app.painters.interclass.InterfejsPainter;
import raf.dsw.classycraft.app.painters.interclass.KlasaPainter;

import java.awt.*;
import java.awt.geom.Point2D;

public class DuplicateState implements State{
    @Override
    public void misKliknut(Point pp, DiagramView dv) {
        Point2D.Double point= new Point2D.Double(pp.x ,pp.y);
        Point2D.Double p = dv.getOriginalCoordinates(point);
        Interclass provera = provera(p,dv);
        if(provera!=null){
            try {
                Interclass duplikatInterclass = (Interclass) provera.clone();
                if(provera instanceof Klasa){
                    KlasaPainter duplikatPainter = new KlasaPainter(duplikatInterclass);
                    dv.getPainters().add(duplikatPainter);
                    MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(),duplikatInterclass);
                    //dv.getDiagram().addChild(duplikatInterclass);
                }else if(provera instanceof Interfejs){
                    InterfejsPainter duplikatPainter = new InterfejsPainter(duplikatInterclass);
                    dv.getPainters().add(duplikatPainter);
                    MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(),duplikatInterclass);
                    //dv.getDiagram().addChild(duplikatInterclass);
                }else if(provera instanceof Enumm){
                    EnumPainter duplikatPainter = new EnumPainter(duplikatInterclass);
                    dv.getPainters().add(duplikatPainter);
                    MainFrame.getInstance().getClassyTree().addDiagramChild(dv.getDiagram(),duplikatInterclass);
                    //dv.getDiagram().addChild(duplikatInterclass);
                }
            } catch (CloneNotSupportedException e) {
                //e.printStackTrace();
            }
        }
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

    public Interclass provera(Point2D.Double p, DiagramView dv){
        if(!dv.getPainters().isEmpty()){
            for(ElementPainter ep:dv.getPainters()){
                if(ep.elementAt(p) && ep instanceof InterclassPainter) {
                    return ((InterclassPainter) ep).getInterclass();
                }
            }
            return null;
        }else{
            return null;
        }
    }
}
